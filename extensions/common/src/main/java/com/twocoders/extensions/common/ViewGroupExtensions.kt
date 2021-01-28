package com.twocoders.extensions.common

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToThisViewGroup: Boolean = false): View =
    context.layoutInflater.inflate(layoutId, this, attachToThisViewGroup)