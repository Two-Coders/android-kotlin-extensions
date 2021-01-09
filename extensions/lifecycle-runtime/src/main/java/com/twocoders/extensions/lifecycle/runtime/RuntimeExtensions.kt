package com.twocoders.extensions.lifecycle.runtime

import android.app.Activity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

fun LifecycleOwner.activity(): Activity = when (this) {
    is Activity -> this
    is Fragment -> requireActivity()
    else -> throw NotImplementedError("Unexpected LifecycleOwner ${this::class}! Only Activity and Fragment are supported now as the LifecycleOwner.")
}

fun LifecycleOwner.isFinishing() = when (this) {
    is Fragment -> this.isRemoving
    is Activity -> this.isFinishing
    else -> false
}