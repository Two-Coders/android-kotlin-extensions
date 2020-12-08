package com.twocoders.util.commonextensions

import android.os.Bundle
import android.os.Parcelable

fun Bundle?.getInt(key: String, defaultValue: Int): Int = this?.getInt(key, defaultValue) ?: defaultValue

fun Bundle?.getBoolean(key: String, defaultValue: Boolean): Boolean = this?.getBoolean(key, defaultValue) ?: defaultValue

fun Bundle?.getString(key: String): String? = this?.getString(key)

fun Bundle?.getString(key: String, defaultValue: String): String = this?.getString(key) ?: defaultValue

fun Bundle?.getCharSequence(key: String): CharSequence? = this?.getCharSequence(key)

fun Bundle?.getCharSequence(key: String, defaultValue: CharSequence): CharSequence = this?.getCharSequence(key) ?: defaultValue

fun <T : Parcelable> Bundle?.getParcelableValue(key: String): T? = this?.getParcelable(key)