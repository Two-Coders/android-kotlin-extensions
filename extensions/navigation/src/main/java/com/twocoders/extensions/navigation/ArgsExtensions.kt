package com.twocoders.extensions.navigation

import android.os.Bundle
import com.twocoders.extensions.navigation.args.ARGS_VIEW_MODEL_BUNDLE_ARGS_KEY

fun Bundle?.toArgsBundle() = if (this != null) {
    Bundle(this).apply {
        putBundle(ARGS_VIEW_MODEL_BUNDLE_ARGS_KEY, this)
    }
} else null
