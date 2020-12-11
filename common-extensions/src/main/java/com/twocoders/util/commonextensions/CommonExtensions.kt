package com.twocoders.util.commonextensions

import android.os.Build

fun isLollipopWithAnExtraSugar(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1
fun isAtLeastLollipopWithAnExtraSugar(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1
fun isMarshmallow(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.M
fun isAtLeastMarshmallow(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougat(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.N
fun isAtLeastNougat(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isNougatWithAnExtraSugar(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1
fun isAtLeastNougatWithAnExtraSugar(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isOreo(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.O
fun isAtLeastOreo(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isOreoWithAnExtraSugar(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1
fun isAtLeastOreoWithAnExtraSugar(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun isP(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.P
fun isAtLeastP(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
fun isQ(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.Q
fun isAtLeastQ(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
fun isR(): Boolean = Build.VERSION.SDK_INT == Build.VERSION_CODES.R
fun isAtLeastR(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

fun androidName(): String = try {
    Build.VERSION_CODES::class.java.fields
        .lastOrNull { it.getInt(Build.VERSION_CODES::class) == Build.VERSION.SDK_INT }?.name ?: EMPTY_STRING
} catch (e: Exception) {
    EMPTY_STRING
}

fun androidBuildName(): String = Build.ID

fun androidVersion(): String = Build.VERSION.RELEASE

fun deviceName(): String = "${Build.MANUFACTURER} ${Build.MODEL}"