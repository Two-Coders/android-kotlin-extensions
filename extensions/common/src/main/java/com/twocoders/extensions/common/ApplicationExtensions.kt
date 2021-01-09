package com.twocoders.extensions.common

import android.app.Application
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes

val Application.applicationName: String
    get() = applicationContext.applicationName

fun Application.hasCamera() = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)
fun Application.hasCameraFlash() = packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)
fun Application.hasBluetooth() = packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)
fun Application.hasGps() = packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)
fun Application.hasMicrophone() = packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)
fun Application.hasNfc() = packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)
fun Application.hasAccelerometer() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)
fun Application.hasGyroscope() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)
fun Application.hasStepCounter() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)
fun Application.hasStepDetector() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)
fun Application.hasCompass() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)
fun Application.hasBarometer() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER)
fun Application.hasHeartRate() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE)
fun Application.hasLightSensor() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)
fun Application.hasProximitySensor() = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY)
@RequiresApi(api = Build.VERSION_CODES.M)
fun Application.hasFingerprint() = packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)
fun Application.hasTouchScreen() = packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)
fun Application.hasUsbHost() = packageManager.hasSystemFeature(PackageManager.FEATURE_USB_HOST)
fun Application.hasWifi() = packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)

fun Application.checkPermission(permission: String) = applicationContext.checkPermission(permission)

fun Application.checkPermissions(permissions: Array<String>): Map<String, Boolean> = applicationContext.checkPermissions(permissions)

fun Application.getMetaData(): Bundle? = try {
    packageManager
        .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        .metaData
} catch (e: PackageManager.NameNotFoundException) {
    null
}

fun Application.getMetaDataStringValue(@StringRes id: Int) = getMetaData()?.getString(getString(id))

fun Application.getMetaDataFloatValue(@StringRes id: Int) = getMetaData()?.getFloat(getString(id))

fun Application.getMetaDataBooleanValue(@StringRes id: Int) = getMetaData()?.getBoolean(getString(id))