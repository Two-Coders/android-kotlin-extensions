package com.twocoders.extensions.material.component

import com.twocoders.dynamic.text.DynamicText

data class AlertDialogComponent(
    val title: DynamicText = DynamicText.EMPTY,
    val message: DynamicText = DynamicText.EMPTY,
    val positiveButtonText: DynamicText = DynamicText.EMPTY,
    val negativeButtonText: DynamicText = DynamicText.EMPTY
)