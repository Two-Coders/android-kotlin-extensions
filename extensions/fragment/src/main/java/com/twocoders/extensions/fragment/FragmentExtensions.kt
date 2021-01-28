package com.twocoders.extensions.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.twocoders.extensions.activity.enterImmersiveMode
import com.twocoders.extensions.activity.exitImmersiveMode
import com.twocoders.extensions.common.*

fun Fragment.startActivity(activity: Class<out Activity>) =
    requireActivity().startActivity(activity)

fun Fragment.startActivity(intentAction: String = Intent.ACTION_VIEW, uri: Uri) =
    requireActivity().startActivity(intentAction, uri)

fun Fragment.startDialActivity(uri: Uri) =
    requireActivity().startDialActivity(uri)

fun Fragment.startApplicationSystemSettingsActivity() =
    requireActivity().startApplicationSystemSettingsActivity()

fun Fragment.startLocationSettingsActivity() =
    requireActivity().startLocationSettingsActivity()

fun Fragment.startYouTubeActivity(youTubeVideoId: String) =
    requireActivity().startYouTubeActivity(youTubeVideoId)

fun Fragment.startWebBrowserActivity(url: String): Boolean =
    requireActivity().startWebBrowserActivity(url)

fun Fragment.startGooglePlayActivity(appPackageName: String = requireActivity().packageName, flags: Int? = null) =
    requireActivity().startGooglePlayActivity(appPackageName, flags)

fun Fragment.registerPermissionLauncher(callback: ActivityResultCallback<Boolean>) =
    requireActivity().registerForActivityResult(ActivityResultContracts.RequestPermission(), callback)

fun Fragment.registerMultiplePermissionsLauncher(callback: ActivityResultCallback<Map<String, Boolean>>) =
    requireActivity().registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), callback)

fun Fragment.addOnWindowFocusChangeListener(callback: (hasFocus: Boolean) -> Unit) =
    view?.viewTreeObserver?.addOnWindowFocusChangeListener(callback)

fun Fragment.removeOnWindowFocusChangeListener(callback: (hasFocus: Boolean) -> Unit) =
    view?.viewTreeObserver?.removeOnWindowFocusChangeListener(callback)

fun Fragment.enterImmersiveMode() = requireActivity().enterImmersiveMode()

fun Fragment.exitImmersiveMode() = requireActivity().exitImmersiveMode()
