package com.twocoders.extensions.lifecycle.livedata

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.twocoders.extensions.common.get
import com.twocoders.extensions.common.set
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.reflect.KClass

@Suppress("MemberVisibilityCanBePrivate")
open class SharedPreferencesLiveData<T : Any>(
    private val classType: KClass<T>,
    protected val prefs: SharedPreferences,
    protected val prefKey: String,
    protected val defValue: T
) : MutableLiveData<T>() {

    private val preferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == this.prefKey) {
            CoroutineScope(Dispatchers.Main).launch {
                value = getValueFromPrefs()
            }
        }
    }

    override fun getValue(): T = getValueFromPrefs()

    override fun setValue(newValue: T) {
        super.setValue(newValue)

        if (value != newValue) setValueToPrefs(newValue)
    }

    override fun postValue(newValue: T) {
        super.postValue(newValue)

        if (value != newValue) setValueToPrefs(newValue)
    }

    override fun onActive() {
        super.onActive()
        value = getValueFromPrefs()
        prefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener)
    }

    override fun onInactive() {
        prefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener)
        super.onInactive()
    }

    protected open fun getValueFromPrefs(): T = prefs[classType, prefKey, defValue]

    protected open fun setValueToPrefs(newValue: T) {
        prefs[classType, prefKey] = newValue
    }
}