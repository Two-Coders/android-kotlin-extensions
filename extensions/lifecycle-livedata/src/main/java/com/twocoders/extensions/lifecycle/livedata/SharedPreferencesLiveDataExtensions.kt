package com.twocoders.extensions.lifecycle.livedata

import android.content.SharedPreferences

inline fun <reified T : Any> SharedPreferences.liveData(key: String, defValue: T) =
    SharedPreferencesLiveData(T::class, this, key, defValue)