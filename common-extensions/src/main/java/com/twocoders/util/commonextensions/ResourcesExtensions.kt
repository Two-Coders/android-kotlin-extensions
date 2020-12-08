package com.twocoders.util.commonextensions

import android.content.res.Resources
import android.util.TypedValue
import androidx.annotation.DimenRes

fun Resources.getDimensionScalablePixelSize(@DimenRes id: Int) =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, getDimension(id), displayMetrics).toInt()