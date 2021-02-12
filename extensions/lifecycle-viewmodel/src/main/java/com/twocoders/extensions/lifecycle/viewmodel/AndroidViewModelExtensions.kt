package com.twocoders.extensions.lifecycle.viewmodel

import android.app.*
import android.app.usage.NetworkStatsManager
import android.content.ClipboardManager
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.location.LocationManager
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.*
import android.os.storage.StorageManager
import android.telephony.TelephonyManager
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.lifecycle.AndroidViewModel
import com.twocoders.extensions.common.*
import java.io.File
import java.util.*

val AndroidViewModel.app
    get() = getApplication<Application>()

val AndroidViewModel.audioManager: AudioManager?
    get() = app.audioManager

val AndroidViewModel.locationManager: LocationManager?
    get() = app.locationManager

val AndroidViewModel.clipboardManager: ClipboardManager?
    get() = app.clipboardManager

val AndroidViewModel.inputMethodManager: InputMethodManager?
    get() = app.inputMethodManager

val AndroidViewModel.notificationManager: NotificationManager?
    get() = app.notificationManager

val AndroidViewModel.connectivityManager: ConnectivityManager?
    get() = app.connectivityManager

val AndroidViewModel.networkStatsManager: NetworkStatsManager?
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = app.networkStatsManager

val AndroidViewModel.wifiManager: WifiManager?
    get() = app.wifiManager

val AndroidViewModel.windowManager: WindowManager?
    get() = app.windowManager

val AndroidViewModel.activityManager: ActivityManager?
    get() = app.activityManager

val AndroidViewModel.camera2Manager: CameraManager?
    get() = app.camera2Manager

val AndroidViewModel.sensorManager: SensorManager?
    get() = app.sensorManager

val AndroidViewModel.powerManager: PowerManager?
    get() = app.powerManager

val AndroidViewModel.alarmManager: AlarmManager?
    get() = app.alarmManager

val AndroidViewModel.keyguardManager: KeyguardManager?
    get() = app.keyguardManager

val AndroidViewModel.searchManager: SearchManager?
    get() = app.searchManager

val AndroidViewModel.storageManager: StorageManager?
    get() = app.storageManager

val AndroidViewModel.vibratorManager: Vibrator?
    get() = app.vibratorManager

val AndroidViewModel.telephonyManager: TelephonyManager?
    get() = app.telephonyManager

val AndroidViewModel.uiModeManager: UiModeManager?
    get() = app.uiModeManager

val AndroidViewModel.downloadManager: DownloadManager?
    get() = app.downloadManager

val AndroidViewModel.batteryManager: BatteryManager?
    get() = app.batteryManager

val AndroidViewModel.hardwarePropertiesManager: HardwarePropertiesManager?
    @RequiresApi(api = Build.VERSION_CODES.N)
    get() = app.hardwarePropertiesManager

val AndroidViewModel.hasCamera: Boolean
    get() = app.hasCamera

val AndroidViewModel.hasCameraFlash: Boolean
    get() = app.hasCameraFlash

val AndroidViewModel.hasBluetooth: Boolean
    get() = app.hasBluetooth

val AndroidViewModel.hasGps: Boolean
    get() = app.hasGps

val AndroidViewModel.hasMicrophone: Boolean
    get() = app.hasMicrophone

val AndroidViewModel.hasNfc: Boolean
    get() = app.hasNfc

val AndroidViewModel.hasAccelerometer: Boolean
    get() = app.hasAccelerometer

val AndroidViewModel.hasGyroscope: Boolean
    get() = app.hasGyroscope

val AndroidViewModel.hasStepCounter: Boolean
    get() = app.hasStepCounter

val AndroidViewModel.hasStepDetector: Boolean
    get() = app.hasStepDetector

val AndroidViewModel.hasCompass: Boolean
    get() = app.hasCompass

val AndroidViewModel.hasBarometer: Boolean
    get() = app.hasBarometer

val AndroidViewModel.hasHeartRate: Boolean
    get() = app.hasHeartRate

val AndroidViewModel.hasLightSensor: Boolean
    get() = app.hasLightSensor

val AndroidViewModel.hasProximitySensor: Boolean
    get() = app.hasProximitySensor

val AndroidViewModel.hasFingerprint: Boolean
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = app.hasFingerprint

val AndroidViewModel.hasTouchScreen: Boolean
    get() = app.hasTouchScreen

val AndroidViewModel.hasUsbHost: Boolean
    get() = app.hasUsbHost

val AndroidViewModel.hasWifi: Boolean
    get() = app.hasWifi

val AndroidViewModel.appName
    get() = app.appName

val AndroidViewModel.locale: Locale?
    get() = app.locale

val AndroidViewModel.localeLanguage: String
    get() = app.localeLanguage

val AndroidViewModel.isRtl: Boolean
    get() = app.isRtl

val AndroidViewModel.isNightModeEnabled: Boolean
    get() = app.isNightModeEnabled

val AndroidViewModel.isNetworkAvailable: Boolean
    get() = app.isNetworkAvailable

val AndroidViewModel.isNetworkUnavailable: Boolean
    get() = !isNetworkAvailable

fun AndroidViewModel.checkPermission(permission: String) = app.checkPermission(permission)

fun AndroidViewModel.checkPermissions(permissions: Array<String>): Map<String, Boolean> = app.checkPermissions(permissions)

val AndroidViewModel.metaData: Bundle?
    get() = app.metaData

fun AndroidViewModel.getMetaDataStringValue(@StringRes id: Int) = app.getMetaDataStringValue(id)

fun AndroidViewModel.getMetaDataFloatValue(@StringRes id: Int) = app.getMetaDataFloatValue(id)

fun AndroidViewModel.getMetaDataBooleanValue(@StringRes id: Int) = app.getMetaDataBooleanValue(id)

val AndroidViewModel.internalPrivateDir: File
    get() = app.internalPrivateDir

val AndroidViewModel.internalPrivateCacheDir: File
    get() = app.internalPrivateCacheDir

val AndroidViewModel.externalPrivateDir: File?
    get() = app.externalPrivateDir

val AndroidViewModel.externalPrivateCacheDir: File?
    get() = app.externalPrivateCacheDir

val AndroidViewModel.externalPrivateDirs: Array<out File?>
    get() = app.externalPrivateDirs

val AndroidViewModel.externalPrivateCacheDirs: Array<out File?>
    get() = app.externalPrivateCacheDirs
