package com.twocoders.extensions.common

import android.content.SharedPreferences
import java.security.spec.InvalidParameterSpecException
import java.util.*
import kotlin.reflect.KClass

/**
 * finds value on given key.
 * [T] is the type of value
 * @param defaultValue optional default value - will take null for strings, false for bool and -1 for numeric values if [defaultValue] is not specified
 */
inline operator fun <reified T : Any> SharedPreferences.get(key: String, defaultValue: T? = null): T = get(T::class, key, defaultValue)

@Suppress("UNCHECKED_CAST")
operator fun <T : Any> SharedPreferences.get(classType: KClass<T>, key: String, defaultValue: T? = null): T =
    when (classType) {
        Boolean::class -> getBoolean(key, defaultValue as? Boolean ?: false) as T
        Float::class -> getFloat(key, defaultValue as? Float ?: -1f) as T
        Int::class -> getInt(key, defaultValue as? Int ?: -1) as T
        Long::class -> getLong(key, defaultValue as? Long ?: -1) as T
        String::class -> getString(key, defaultValue as? String) as T
        Date::class -> { Date(getLong(key, (defaultValue as? Date)?.time ?: 0L)) as T }
        else -> throw UnsupportedOperationException("Sorry, this classType $classType is currently not supported :/")
    }

/**
 * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
 */
inline operator fun <reified T : Any> SharedPreferences.set(key: String, value: T) = set(T::class, key, value)

operator fun <T : Any> SharedPreferences.set(classType: KClass<T>, key: String, value: T) {
    if (key.isEmpty()) {
        throw InvalidParameterSpecException("Invalid key")
    }

    val editor = edit()
    when (classType) {
        Boolean::class -> editor.putBoolean(key, value as Boolean)
        Float::class -> editor.putFloat(key, value as Float)
        Int::class -> editor.putInt(key, value as Int)
        Long::class -> editor.putLong(key, value as Long)
        String::class -> editor.putString(key, value as String)
        Date::class -> editor.putLong(key, (value as Date).time)
        else -> throw UnsupportedOperationException("Sorry, this classType $classType is currently not supported :/")
    }
    editor.apply()
}