package com.twocoders.extensions.common

import android.os.Build

val isLollipopWithAnExtraSugar: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1

val isAtLeastLollipopWithAnExtraSugar: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1

val isMarshmallow: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.M

val isAtLeastMarshmallow: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

val isNougat: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.N

val isAtLeastNougat: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

val isNougatWithAnExtraSugar: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1

val isAtLeastNougatWithAnExtraSugar: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1

val isOreo: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O

val isAtLeastOreo: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

val isOreoWithAnExtraSugar: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1

val isAtLeastOreoWithAnExtraSugar: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1

val isP: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.P

val isAtLeastP: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P

val isQ: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.Q

val isAtLeastQ: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

val isR: Boolean
    get() = Build.VERSION.SDK_INT == Build.VERSION_CODES.R

val isAtLeastR: Boolean
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R

val androidName: String
    get() = try {
        Build.VERSION_CODES::class.java.fields
            .lastOrNull { it.getInt(Build.VERSION_CODES::class) == Build.VERSION.SDK_INT }?.name
            ?: EMPTY_STRING
    } catch (e: Exception) {
        EMPTY_STRING
    }

val androidBuildName: String = Build.ID

val androidVersion: String = Build.VERSION.RELEASE

val deviceName: String = "${Build.MANUFACTURER} ${Build.MODEL}"