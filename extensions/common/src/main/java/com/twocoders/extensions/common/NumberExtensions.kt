package com.twocoders.extensions.common

import java.text.NumberFormat

fun Number.format(minFractionDigits: Int = 0, maxFractionDigits: Int = 0): String =
    NumberFormat.getNumberInstance()
        .apply {
            minimumFractionDigits = minFractionDigits
            maximumFractionDigits = maxFractionDigits
        }.format(this)