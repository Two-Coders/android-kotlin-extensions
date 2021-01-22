package com.twocoders.extensions.common

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
import java.io.File

val Application.audioManager: AudioManager?
    get() = applicationContext.audioManager

val Application.locationManager: LocationManager?
    get() = applicationContext.locationManager

val Application.clipboardManager: ClipboardManager?
    get() = applicationContext.clipboardManager

val Application.inputMethodManager: InputMethodManager?
    get() = applicationContext.inputMethodManager

val Application.notificationManager: NotificationManager?
    get() = applicationContext.notificationManager

val Application.connectivityManager: ConnectivityManager?
    get() = applicationContext.connectivityManager

val Application.networkStatsManager: NetworkStatsManager?
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = applicationContext.networkStatsManager

val Application.wifiManager: WifiManager?
    get() = applicationContext.wifiManager

val Application.windowManager: WindowManager?
    get() = applicationContext.windowManager

val Application.activityManager: ActivityManager?
    get() = applicationContext.activityManager

val Application.camera2Manager: CameraManager?
    get() = applicationContext.camera2Manager

val Application.sensorManager: SensorManager?
    get() = applicationContext.sensorManager

val Application.powerManager: PowerManager?
    get() = applicationContext.powerManager

val Application.alarmManager: AlarmManager?
    get() = applicationContext.alarmManager

val Application.keyguardManager: KeyguardManager?
    get() = applicationContext.keyguardManager

val Application.searchManager: SearchManager?
    get() = applicationContext.searchManager

val Application.storageManager: StorageManager?
    get() = applicationContext.storageManager

val Application.vibratorManager: Vibrator?
    get() = applicationContext.vibratorManager

val Application.telephonyManager: TelephonyManager?
    get() = applicationContext.telephonyManager

val Application.uiModeManager: UiModeManager?
    get() = applicationContext.uiModeManager

val Application.downloadManager: DownloadManager?
    get() = applicationContext.downloadManager

val Application.batteryManager: BatteryManager?
    get() = applicationContext.batteryManager

val Application.hardwarePropertiesManager: HardwarePropertiesManager?
    @RequiresApi(api = Build.VERSION_CODES.N)
    get() = applicationContext.hardwarePropertiesManager

val Application.hasCamera: Boolean
    get() = applicationContext.hasCamera

val Application.hasCameraFlash: Boolean
    get() = applicationContext.hasCameraFlash

val Application.hasBluetooth: Boolean
    get() = applicationContext.hasBluetooth

val Application.hasGps: Boolean
    get() = applicationContext.hasGps

val Application.hasMicrophone: Boolean
    get() = applicationContext.hasMicrophone

val Application.hasNfc: Boolean
    get() = applicationContext.hasNfc

val Application.hasAccelerometer: Boolean
    get() = applicationContext.hasAccelerometer

val Application.hasGyroscope: Boolean
    get() = applicationContext.hasGyroscope

val Application.hasStepCounter: Boolean
    get() = applicationContext.hasStepCounter

val Application.hasStepDetector: Boolean
    get() = applicationContext.hasStepDetector

val Application.hasCompass: Boolean
    get() = applicationContext.hasCompass

val Application.hasBarometer: Boolean
    get() = applicationContext.hasBarometer

val Application.hasHeartRate: Boolean
    get() = applicationContext.hasHeartRate

val Application.hasLightSensor: Boolean
    get() = applicationContext.hasLightSensor

val Application.hasProximitySensor: Boolean
    get() = applicationContext.hasProximitySensor

val Application.hasFingerprint: Boolean
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = applicationContext.hasFingerprint

val Application.hasTouchScreen: Boolean
    get() = applicationContext.hasTouchScreen

val Application.hasUsbHost: Boolean
    get() = applicationContext.hasUsbHost

val Application.hasWifi: Boolean
    get() = applicationContext.hasWifi

val Application.appName: String
    get() = applicationContext.appName

val Application.isRtl: Boolean
    get() = applicationContext.isRtl

val Application.isNightModeEnabled: Boolean
    get() = applicationContext.isNightModeEnabled

val Application.isNetworkAvailable: Boolean
    get() = applicationContext.isNetworkAvailable

val Application.isNetworkUnavailable: Boolean
    get() = !isNetworkAvailable

fun Application.checkPermission(permission: String) = applicationContext.checkPermission(permission)

fun Application.checkPermissions(permissions: Array<String>): Map<String, Boolean> = applicationContext.checkPermissions(permissions)

fun Application.hasPermissionInManifest(permissionName: String) = applicationContext.hasPermissionInManifest(permissionName)

val Application.metaData: Bundle?
    get() = applicationContext.metaData

fun Application.getMetaDataStringValue(@StringRes id: Int) = applicationContext.getMetaDataStringValue(id)

fun Application.getMetaDataFloatValue(@StringRes id: Int) = applicationContext.getMetaDataFloatValue(id)

fun Application.getMetaDataBooleanValue(@StringRes id: Int) = applicationContext.getMetaDataBooleanValue(id)

val Application.internalPrivateDir: File
    get() = applicationContext.internalPrivateDir

val Application.internalPrivateCacheDir: File
    get() = applicationContext.internalPrivateCacheDir

val Application.externalPrivateDir: File?
    get() = applicationContext.externalPrivateDir

val Application.externalPrivateCacheDir: File?
    get() = applicationContext.externalPrivateCacheDir

val Application.externalPrivateDirs: Array<out File?>
    get() = applicationContext.externalPrivateDirs

val Application.externalPrivateCacheDirs: Array<out File?>
    get() = applicationContext.externalPrivateCacheDirs
