package com.twocoders.extensions.navigation.args

import android.app.Application
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy

internal const val ARGS_VIEW_MODEL_BUNDLE_ARGS_KEY = "args_view_model_bundle_args_key"

open class ArgsAndroidViewModel(
    application: Application,
    private val savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    protected val arguments
        get() = savedStateHandle.get<Bundle>(ARGS_VIEW_MODEL_BUNDLE_ARGS_KEY)

    @MainThread
    protected inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        arguments ?: throw IllegalStateException("ViewModel $this has null arguments")
    }
}