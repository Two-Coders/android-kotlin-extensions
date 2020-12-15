package com.twocoders.extensions.common

import android.location.Location

fun Location.verticalAccuracy() =
    if (isAtLeastOreo()) {
        verticalAccuracyMeters
    } else {
        accuracy
    }