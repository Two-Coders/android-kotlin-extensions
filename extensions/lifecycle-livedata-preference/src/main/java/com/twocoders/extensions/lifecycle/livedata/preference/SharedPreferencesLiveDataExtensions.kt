package com.twocoders.extensions.lifecycle.livedata.preference

import android.content.SharedPreferences
import java.security.spec.InvalidParameterSpecException
import java.util.*
import kotlin.reflect.full.isSubclassOf

inline fun <reified T : Any> SharedPreferences.liveData(key: String, defValue: T) =
    object : SharedPreferencesLiveData<T>(this, key, defValue) {
        override fun getValueFromPrefs(): T = prefs[prefKey, defValue]
        override fun setValueToPrefs(newValue: T) { prefs[prefKey] = newValue }
    }

/**
 * finds value on given key.
 * [T] is the type of value
 * @param defaultValue optional default value - will take null for strings, false for bool and -1
 * for numeric values if [defaultValue] is not specified
 */
inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T = when {
    T::class == Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
    T::class == Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
    T::class == Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
    T::class == Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
    T::class == String::class -> getString(key, defaultValue as? String) as T
    T::class == Date::class -> Date(getLong(key, (defaultValue as? Date)?.time ?: 0L)) as T
    T::class.isSubclassOf(Enum::class) -> {
        with(T::class.java.enumConstants as Array<T>) {
            val index = getInt(key, (defaultValue as? Enum<*>)?.ordinal ?: Int.MAX_VALUE)
            if (this.size > index) this[index] else (defaultValue ?: this[0])
        }
    }
    else -> throw UnsupportedOperationException("Sorry, this class ${T::class} is currently not supported :/")
}

/**
 * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
 */
inline operator fun <reified T : Any> SharedPreferences.set(key: String, value: T) {
    if (key.isEmpty()) {
        throw InvalidParameterSpecException("Invalid key")
    }

    with(edit()) {
        when {
            T::class == Boolean::class -> putBoolean(key, value as Boolean)
            T::class == Float::class -> putFloat(key, value as Float)
            T::class == Int::class -> putInt(key, value as Int)
            T::class == Long::class -> putLong(key, value as Long)
            T::class == String::class -> putString(key, value as String)
            T::class == Date::class -> putLong(key, (value as Date).time)
            T::class.isSubclassOf(Enum::class) -> putInt(key, (value as Enum<*>).ordinal)
            else -> throw UnsupportedOperationException("Sorry, this class ${T::class} is currently not supported :/")
        }
        apply()
    }
}