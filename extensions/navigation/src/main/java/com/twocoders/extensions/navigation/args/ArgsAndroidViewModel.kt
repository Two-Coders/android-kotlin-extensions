package com.twocoders.extensions.navigation.args

import android.app.Application
import android.os.Bundle
import androidx.annotation.MainThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import androidx.navigation.NavArgsLazy

const val ARGS_VIEW_MODEL_BUNDLE_ARGS_KEY = "args_view_model_bundle_args_key"

open class ArgsAndroidViewModel(
    application: Application,
    val navArgsSavedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    @MainThread
    inline fun <reified Args : NavArgs> navArgs() = NavArgsLazy(Args::class) {
        navArgsSavedStateHandle.get<Bundle>(ARGS_VIEW_MODEL_BUNDLE_ARGS_KEY)
            ?: throw IllegalStateException("ViewModel $this has null arguments")
    }
}