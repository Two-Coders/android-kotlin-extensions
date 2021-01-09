package com.twocoders.extensions.activity

import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.net.Uri
import android.provider.Settings
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Activity.bindLayout(@LayoutRes layoutId: Int): T =
    DataBindingUtil.setContentView(this, layoutId)

fun ActivityResultLauncher<IntentSenderRequest>.launchIntentSender(intentSender: IntentSender) {
    launch(IntentSenderRequest.Builder(intentSender).build())
}

fun Activity.startApplicationSystemSettingsActivity() {
    Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:$packageName")).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }.also { intent ->
        startActivity(intent)
    }
}
