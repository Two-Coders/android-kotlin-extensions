package com.twocoders.extensions.common

import android.view.Window
import android.view.WindowManager

val Window?.softInputMode
    get() = this?.attributes?.softInputMode ?: WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED