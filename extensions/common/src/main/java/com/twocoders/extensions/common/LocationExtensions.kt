package com.twocoders.extensions.common

import android.location.Location

val Location.verticalAccuracy
    get() = if (isAtLeastOreo) verticalAccuracyMeters else accuracy