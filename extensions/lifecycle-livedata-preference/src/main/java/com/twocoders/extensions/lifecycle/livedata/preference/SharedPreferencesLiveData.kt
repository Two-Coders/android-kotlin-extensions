package com.twocoders.extensions.lifecycle.livedata.preference

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("MemberVisibilityCanBePrivate")
abstract class SharedPreferencesLiveData<T : Any>(
    protected val prefs: SharedPreferences,
    protected val prefKey: String,
    protected val defValue: T
) : MutableLiveData<T>() {

    abstract fun getValueFromPrefs(): T
    abstract fun setValueToPrefs(newValue: T)

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
}