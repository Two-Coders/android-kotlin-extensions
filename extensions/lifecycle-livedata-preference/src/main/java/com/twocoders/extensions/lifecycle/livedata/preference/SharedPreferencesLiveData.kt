package com.twocoders.extensions.lifecycle.livedata.preference

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData

@Suppress("MemberVisibilityCanBePrivate")
abstract class SharedPreferencesLiveData<T : Any>(
    protected val prefs: SharedPreferences,
    protected val prefKey: String,
    protected val defValue: T
) : MutableLiveData<T>() {

    protected abstract fun getValueFromPrefs(): T
    protected abstract fun setValueToPrefs(newValue: T)

    private val preferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            if (key == this.prefKey) {
                super.postValue(getValueFromPrefs())
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