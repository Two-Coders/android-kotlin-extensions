package com.twocoders.extensions.activity

import android.app.Activity
import android.content.IntentSender
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.twocoders.extensions.common.isAtLeastR

fun <T : ViewDataBinding> Activity.bindLayout(@LayoutRes layoutId: Int): T =
    DataBindingUtil.setContentView(this, layoutId)

fun ActivityResultLauncher<IntentSenderRequest>.launchIntentSender(intentSender: IntentSender) {
    launch(IntentSenderRequest.Builder(intentSender).build())
}

fun Activity.enterImmersiveMode() {
    if (isAtLeastR) {
        window.setDecorFitsSystemWindows(false)
        window.insetsController?.let {
            it.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }
}

fun Activity.exitImmersiveMode() {
    if (isAtLeastR) {
        window.setDecorFitsSystemWindows(true)
        window.insetsController?.let {
            it.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            it.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_TOUCH
        }
    } else {
        @Suppress("DEPRECATION")
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    }
}