package com.twocoders.extensions.lifecycle.viewmodel.savedstate

import androidx.lifecycle.SavedStateHandle

fun <T> SavedStateHandle.request(key: String) = get<T>(key)
    ?: throw IllegalArgumentException("Invalid key: $key or the requested value is not defined.")