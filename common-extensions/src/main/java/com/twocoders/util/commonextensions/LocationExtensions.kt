package com.twocoders.util.commonextensions

import android.location.Location

fun Location.verticalAccuracy() =
    if (isAtLeastOreo()) {
        verticalAccuracyMeters
    } else {
        accuracy
    }