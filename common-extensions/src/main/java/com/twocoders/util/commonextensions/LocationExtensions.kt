package com.twocoders.util.commonextensions

import android.location.Location

fun Location.verticalAccuracy() =
    if (isOreo()) {
        verticalAccuracyMeters
    } else {
        accuracy
    }