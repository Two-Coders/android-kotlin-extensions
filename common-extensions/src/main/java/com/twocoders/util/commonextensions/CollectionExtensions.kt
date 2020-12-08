package com.twocoders.util.commonextensions

import android.util.SparseArray

val SparseArray<*>.max: Int get() = size() - 1

inline fun <T> SparseArray<T>.forEach(action: (key: Int, value: T) -> Unit) {
    for (index in 0 until size()) {
        action(keyAt(index), valueAt(index))
    }
}

inline fun <T, R : Comparable<R>> SparseArray<T>.sortedBy(crossinline selector: (T) -> R?): List<T> {
    val list = ArrayList<T>()
    for (index in 0..max) {
        list.add(get(keyAt(index)))
    }
    return list.sortedBy(selector)
}

inline fun <V1, V2> SparseArray<V1>.map(mapper: (V1) -> V2): List<V2> {
    val mapped = ArrayList<V2>()
    for (index in 0..max) {
        mapped.add(mapper(get(keyAt(index))))
    }
    return mapped
}