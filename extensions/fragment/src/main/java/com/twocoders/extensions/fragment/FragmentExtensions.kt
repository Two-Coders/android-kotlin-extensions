package com.twocoders.extensions.fragment

import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.twocoders.extensions.activity.startApplicationSystemSettingsActivity

fun Fragment.startApplicationSystemSettingsActivity() =
    requireActivity().startApplicationSystemSettingsActivity()

fun Fragment.registerPermissionLauncher(callback: ActivityResultCallback<Boolean>) =
    requireActivity().registerForActivityResult(ActivityResultContracts.RequestPermission(), callback)

fun Fragment.registerMultiplePermissionsLauncher(callback: ActivityResultCallback<Map<String, Boolean>>) =
    requireActivity().registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions(), callback)
