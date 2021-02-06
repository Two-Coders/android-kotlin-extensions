package com.twocoders.extensions.lifecycle.livedata

import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun <T : Any?> LiveData<T>.asLiveEvent(): LiveEvent<T> =
    if (this is LiveEvent<T>) this else throw IllegalArgumentException("$this is not an instance of LiveEvent!")

fun <T : Any?> LiveData<T>.asMutable(): MutableLiveData<T> =
    if (this is MutableLiveData<T>) this else throw IllegalArgumentException("$this is not an instance of MutableLiveData!")

fun <T : Any?> LiveEvent<T>.call() {
    this.value = null
}

fun <T : Any?> LiveEvent<T>.postCall() {
    this.postValue(null)
}

fun LiveData<String>.isEmpty(): Boolean = value?.isEmpty() ?: true

fun LiveData<String>.isNotEmpty() = !isEmpty()

fun LiveData<Boolean>.isTrue() = value == true

fun LiveData<Boolean>.isNotTrue() = !isTrue()

fun MutableLiveData<Boolean>.toggle() {
    value = !value!!
}

fun <T : Any?> MutableLiveData<T>.notifyObservers() {
    this.value = this.value
}

fun <K, V> MutableLiveData<Map<K, V>>.put(key: K, newValue: V) {
    with(this.value) {
        if (this is MutableMap) {
            this[key] = newValue
            notifyObservers()
        } else {
            throw IllegalArgumentException("$this is not an instance of MutableMap!")
        }
    }
}

@MainThread
fun <T> LiveData<T>.observeOnce(observer: (T) -> Unit) {
    observeForever(object : Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer(value)
        }
    })
}

@MainThread
fun <T> LiveData<T>.observeOnce(owner: LifecycleOwner, observer: (T) -> Unit) {
    observe(owner, object : Observer<T> {
        override fun onChanged(value: T) {
            removeObserver(this)
            observer(value)
        }
    })
}

@MainThread
fun <X> LiveData<X>.filter(test: (X) -> Boolean): LiveData<X> {
    val result = MediatorLiveData<X>()

    result.addSource(this) {
        if (it != null && test(it)) {
            result.value = it
        }
    }

    return result
}

@MainThread
fun <X> LiveData<X>.filterNullables(test: (X?) -> Boolean): LiveData<X> {
    val result = MediatorLiveData<X>()

    result.addSource(this) {
        if (test(it)) {
            result.value = it
        }
    }

    return result
}

@MainThread
fun <X> LiveData<X?>.filterNull(): LiveData<X> {
    val result = MediatorLiveData<X>()

    result.addSource(this) {
        it?.let {
            result.value = it
        }
    }

    return result
}

@MainThread
fun <X> LiveData<X>.take(count: Int): LiveData<X> {
    var taken = 0
    val result = MediatorLiveData<X>()

    result.addSource(this) {
        it?.let {
            if (taken < count) {
                result.value = it
                taken++
            }

            if (taken >= count) {
                result.removeSource(this)
            }
        }
    }

    return result
}

@MainThread
fun <X> LiveData<X>.skip(count: Int): LiveData<X> {
    var skipped = 0
    val result = MediatorLiveData<X>()

    result.addSource(this) {
        it?.let {
            if (skipped >= count) {
                result.value = it
            } else {
                skipped++
            }
        }
    }

    return result
}

@MainThread
fun <X> LiveData<X>.withTimeout(timeout: Long, callback: (() -> Unit)? = null) = object : MediatorLiveData<X>() {

    val timer = CoroutineScope(Dispatchers.Main).launch {
        delay(timeout)
        removeSource(this@withTimeout)
        callback?.invoke()
    }

    init {
        addSource(this@withTimeout) {
            timer.cancel()
            value = it
        }
    }

    override fun onInactive() {
        timer.cancel()
        super.onInactive()
    }
}

/**
 * Combines the latest values from two LiveData objects.
 * First emits after both LiveData objects have emitted a value, and will emit afterwards after any
 * of them emits a new value.
 *
 * The difference between combineLatest and zip is that the zip only emits after all LiveData
 * objects have a new value, but combineLatest will emit after any of them has a new value.
 */
@MainThread
fun <A, B> LiveData<A>.combineLatest(second: LiveData<B>): LiveData<Pair<A, B>> = combineLatest(this, second)

/**
 * @see combineLatest
 */
@MainThread
fun <A, B> combineLatest(first: LiveData<A>, second: LiveData<B>) = combineLatest(first, second) { a, b -> Pair(a, b) }

/**
 * @see combineLatest
 */
@MainThread
@Suppress("UNCHECKED_CAST")
fun <A, B, R> combineLatest(first: LiveData<A>, second: LiveData<B>, combineFunction: (A, B) -> R) = MediatorLiveData<R>().apply {

    var firstEmitted = false
    var firstValue: A? = null

    var secondEmitted = false
    var secondValue: B? = null

    addSource(first) {
        firstEmitted = true
        firstValue = it
        if (firstEmitted && secondEmitted) {
            value = combineFunction(firstValue as A, secondValue as B)
        }
    }

    addSource(second) {
        secondEmitted = true
        secondValue = it
        if (firstEmitted && secondEmitted) {
            value = combineFunction(firstValue as A, secondValue as B)
        }
    }
}

/**
 * @see combineLatest
 */
@MainThread
fun <A, B, C> combineLatest(first: LiveData<A>, second: LiveData<B>, third: LiveData<C>) =
    combineLatest(first, second, third) { a, b, c -> Triple(a, b, c) }

/**
 * @see combineLatest
 */
@MainThread
@Suppress("UNCHECKED_CAST")
fun <A, B, C, R> combineLatest(
    first: LiveData<A>,
    second: LiveData<B>,
    third: LiveData<C>,
    combineFunction: (A, B, C) -> R
) = MediatorLiveData<R>().apply {

    var firstEmitted = false
    var firstValue: A? = null

    var secondEmitted = false
    var secondValue: B? = null

    var thirdEmitted = false
    var thirdValue: C? = null

    addSource(first) {
        firstEmitted = true
        firstValue = it
        if (firstEmitted && secondEmitted && thirdEmitted) {
            value = combineFunction(firstValue as A, secondValue as B, thirdValue as C)
        }
    }

    addSource(second) {
        secondEmitted = true
        secondValue = it
        if (firstEmitted && secondEmitted && thirdEmitted) {
            value = combineFunction(firstValue as A, secondValue as B, thirdValue as C)
        }
    }

    addSource(third) {
        thirdEmitted = true
        thirdValue = it
        if (firstEmitted && secondEmitted && thirdEmitted) {
            value = combineFunction(firstValue as A, secondValue as B, thirdValue as C)
        }
    }
}

/**
 * zips both of the LiveData and emits a value after both of them have emitted their values,
 * after that, waits again for both LiveData to emit new value.
 *
 * The difference between combineLatest and zip is that the zip only emits after all LiveData
 * objects have a new value, but combineLatest will emit after any of them has a new value.
 */
@MainThread
fun <A, B> LiveData<A>.zip(second: LiveData<B>): LiveData<Pair<A, B>> = zip(this, second)

@MainThread
fun <A, B> zip(first: LiveData<A>, second: LiveData<B>) = zip(first, second) { a, b -> Pair(a, b) }

@MainThread
@Suppress("UNCHECKED_CAST")
fun <A, B, R> zip(first: LiveData<A>, second: LiveData<B>, zipFunction: (A, B) -> R) = MediatorLiveData<R>().apply {

    var firstEmitted = false
    var firstValue: A? = null

    var secondEmitted = false
    var secondValue: B? = null

    addSource(first) {
        firstEmitted = true
        firstValue = it
        if (firstEmitted && secondEmitted) {
            value = zipFunction(firstValue as A, secondValue as B)
            firstEmitted = false
            secondEmitted = false
        }
    }

    addSource(second) {
        secondEmitted = true
        secondValue = it
        if (firstEmitted && secondEmitted) {
            value = zipFunction(firstValue as A, secondValue as B)
            firstEmitted = false
            secondEmitted = false
        }
    }
}

/**
 * zips three LiveData and emits a value after all of them have emitted their values,
 * after that, waits again for both LiveData to emit new value.
 *
 * The difference between combineLatest and zip is that the zip only emits after all LiveData
 * objects have a new value, but combineLatest will emit after any of them has a new value.
 */
@MainThread
fun <A, B, C> zip(first: LiveData<A>, second: LiveData<B>, third: LiveData<C>) = zip(first, second, third) { a, b, c -> Triple(a, b, c) }

@MainThread
@Suppress("UNCHECKED_CAST")
fun <A, B, C, R> zip(first: LiveData<A>, second: LiveData<B>, third: LiveData<C>, zipFunction: (A, B, C) -> R) = MediatorLiveData<R>().apply {

    var firstEmitted = false
    var firstValue: A? = null

    var secondEmitted = false
    var secondValue: B? = null

    var thirdEmitted = false
    var thirdValue: C? = null

    addSource(first) {
        firstEmitted = true
        firstValue = it
        if (firstEmitted && secondEmitted && thirdEmitted) {
            value = zipFunction(firstValue as A, secondValue as B, thirdValue as C)
            firstEmitted = false
            secondEmitted = false
            thirdEmitted = false
        }
    }

    addSource(second) {
        secondEmitted = true
        secondValue = it
        if (firstEmitted && secondEmitted && thirdEmitted) {
            firstEmitted = false
            secondEmitted = false
            thirdEmitted = false
            value = zipFunction(firstValue as A, secondValue as B, thirdValue as C)
        }
    }

    addSource(third) {
        thirdEmitted = true
        thirdValue = it
        if (firstEmitted && secondEmitted && thirdEmitted) {
            firstEmitted = false
            secondEmitted = false
            thirdEmitted = false
            value = zipFunction(firstValue as A, secondValue as B, thirdValue as C)
        }
    }
}

/**
 * combines the source Observable with other Observables to create an Observable whose values
 * are calculated from the latest values of each, only when the source emits.
 */
@MainThread
@Suppress("UNCHECKED_CAST")
fun <A, B> LiveData<A>.withLatestFrom(from: LiveData<B>): LiveData<Pair<A, B>> = MediatorLiveData<Pair<A, B>>().apply {

    var lastA: A? = null
    var lastFrom: B? = null

    addSource(from) {
        if (lastFrom == null && lastA != null) value = lastA as A to it
        lastFrom = it
    }

    addSource(this@withLatestFrom) {
        if (it == null && value != null) value = null
        lastA = it
        if (lastFrom != null && it != null) value = it to lastFrom as B
    }
}