package com.twocoders.extensions.common

import android.view.Window
import android.view.WindowManager

fun Window?.getSoftInputMode(): Int {
    return this?.attributes?.softInputMode ?: WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED
}