package com.twocoders.extensions.common

import android.annotation.SuppressLint
import android.app.*
import android.app.usage.NetworkStatsManager
import android.content.*
import android.content.Context.*
import android.content.pm.PackageManager
import android.content.res.ColorStateList
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.VectorDrawable
import android.hardware.SensorManager
import android.hardware.camera2.CameraManager
import android.location.LocationManager
import android.media.AudioManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.net.wifi.WifiManager
import android.os.*
import android.os.storage.StorageManager
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import java.io.File
import java.util.*

val Context.audioManager: AudioManager?
    get() = applicationContext.getSystemService(AUDIO_SERVICE) as? AudioManager

val Context.locationManager: LocationManager?
    get() = applicationContext.getSystemService(LOCATION_SERVICE) as? LocationManager

val Context.clipboardManager: ClipboardManager?
    get() = applicationContext.getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager

val Context.inputMethodManager: InputMethodManager?
    get() = applicationContext.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

val Context.notificationManager: NotificationManager?
    get() = applicationContext.getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

val Context.connectivityManager: ConnectivityManager?
    get() = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager

val Context.networkStatsManager: NetworkStatsManager?
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = applicationContext.getSystemService(NETWORK_STATS_SERVICE) as? NetworkStatsManager

val Context.wifiManager: WifiManager?
    get() = applicationContext.getSystemService(WIFI_SERVICE) as? WifiManager

val Context.windowManager: WindowManager?
    get() = applicationContext.getSystemService(WINDOW_SERVICE) as? WindowManager

val Context.activityManager: ActivityManager?
    get() = applicationContext.getSystemService(ACTIVITY_SERVICE) as? ActivityManager

val Context.camera2Manager: CameraManager?
    get() = applicationContext.getSystemService(CAMERA_SERVICE) as? CameraManager

val Context.sensorManager: SensorManager?
    get() = applicationContext.getSystemService(SENSOR_SERVICE) as? SensorManager

val Context.powerManager: PowerManager?
    get() = applicationContext.getSystemService(POWER_SERVICE) as? PowerManager

val Context.alarmManager: AlarmManager?
    get() = applicationContext.getSystemService(ALARM_SERVICE) as? AlarmManager

val Context.keyguardManager: KeyguardManager?
    get() = applicationContext.getSystemService(KEYGUARD_SERVICE) as? KeyguardManager

val Context.searchManager: SearchManager?
    get() = applicationContext.getSystemService(SEARCH_SERVICE) as? SearchManager

val Context.storageManager: StorageManager?
    get() = applicationContext.getSystemService(STORAGE_SERVICE) as? StorageManager

val Context.vibratorManager: Vibrator?
    get() = applicationContext.getSystemService(VIBRATOR_SERVICE) as? Vibrator

val Context.telephonyManager: TelephonyManager?
    get() = applicationContext.getSystemService(TELEPHONY_SERVICE) as? TelephonyManager

val Context.uiModeManager: UiModeManager?
    get() = applicationContext.getSystemService(UI_MODE_SERVICE) as? UiModeManager

val Context.downloadManager: DownloadManager?
    get() = applicationContext.getSystemService(DOWNLOAD_SERVICE) as? DownloadManager

val Context.batteryManager: BatteryManager?
    get() = applicationContext.getSystemService(BATTERY_SERVICE) as? BatteryManager

val Context.hardwarePropertiesManager: HardwarePropertiesManager?
    @RequiresApi(api = Build.VERSION_CODES.N)
    get() = applicationContext.getSystemService(HARDWARE_PROPERTIES_SERVICE) as? HardwarePropertiesManager

val Context.hasCamera: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)

val Context.hasCameraFlash: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)

val Context.hasBluetooth: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH)

val Context.hasGps: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)

val Context.hasMicrophone: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_MICROPHONE)

val Context.hasNfc: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_NFC)

val Context.hasAccelerometer: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_ACCELEROMETER)

val Context.hasGyroscope: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE)

val Context.hasStepCounter: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_COUNTER)

val Context.hasStepDetector: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_STEP_DETECTOR)

val Context.hasCompass: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_COMPASS)

val Context.hasBarometer: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_BAROMETER)

val Context.hasHeartRate: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_HEART_RATE)

val Context.hasLightSensor: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_LIGHT)

val Context.hasProximitySensor: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_PROXIMITY)

val Context.hasFingerprint: Boolean
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_FINGERPRINT)

val Context.hasTouchScreen: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN)

val Context.hasUsbHost: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_USB_HOST)

val Context.hasWifi: Boolean
    get() = applicationContext.packageManager.hasSystemFeature(PackageManager.FEATURE_WIFI)

val Context.appName: String
    get() = with(applicationContext) {
        if (applicationInfo.labelRes != NO_ID) {
            getString(applicationInfo.labelRes)
        } else {
            applicationInfo.nonLocalizedLabel.toString()
        }
    }

val Context.locale: Locale?
    get() = with(applicationContext) {
        @Suppress("DEPRECATION")
        if (isAtLeastNougat) resources.configuration.locales[0] else resources.configuration.locale
    }

val Context.localeLanguage: String
    get() = locale?.language ?: EMPTY_STRING

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

val Context.displayRotation: Int
    get() = (if (isAtLeastR) {
        display
    } else {
        @Suppress("DEPRECATION")
        windowManager?.defaultDisplay
    })?.rotation ?: Surface.ROTATION_0

val Context.isRtl: Boolean
    get() = applicationContext.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL

val Context.isNightModeEnabled: Boolean
    get() = when (applicationContext.resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
        Configuration.UI_MODE_NIGHT_YES -> true
        Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> false
        else -> false
    }

val Context.isNetworkAvailable: Boolean
    get() = connectivityManager?.let {
        if (isAtLeastMarshmallow) {
            it.getNetworkCapabilities(it.activeNetwork)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            @Suppress("DEPRECATION")
            it.activeNetworkInfo?.isConnected
        }
    } ?: false

val Context.isNetworkUnavailable: Boolean
    get() = !isNetworkAvailable

fun Context.checkPermission(permission: String) =
    ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.checkPermissions(permissions: Array<String>): Map<String, Boolean> =
    permissions.associateBy({ it }, { checkPermission(it) })

fun Context.hasPermissionInManifest(permissionName: String) = try {
    applicationContext
        .packageManager
        .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
        .requestedPermissions?.contains(permissionName) ?: false
} catch (e: PackageManager.NameNotFoundException) {
    false
}

val Context.metaData: Bundle?
    get() = try {
    applicationContext
        .packageManager
        .getApplicationInfo(packageName, PackageManager.GET_META_DATA)
        .metaData
} catch (e: PackageManager.NameNotFoundException) {
    null
}

fun Context.getMetaDataStringValue(@StringRes id: Int) = metaData?.getString(getString(id))

fun Context.getMetaDataFloatValue(@StringRes id: Int) = metaData?.getFloat(getString(id))

fun Context.getMetaDataBooleanValue(@StringRes id: Int) = metaData?.getBoolean(getString(id))

val Context.internalPrivateDir: File
    get() = filesDir

val Context.internalPrivateCacheDir: File
    get() = cacheDir

val Context.externalPrivateDir: File?
    get() = getExternalFilesDir(null)

val Context.externalPrivateCacheDir: File?
    get() = externalCacheDir

val Context.externalPrivateDirs: Array<out File?>
    get() = getExternalFilesDirs(null)

val Context.externalPrivateCacheDirs: Array<out File?>
    get() = externalCacheDirs

fun Context.getAnimationDrawable(@DrawableRes drawableResId: Int): AnimationDrawable? = try {
    getDrawable(drawableResId = drawableResId) as? AnimationDrawable
} catch (oom: OutOfMemoryError) {
    logd("Failed to load the AnimationDrawable:", oom)
    null
}

fun Context.getBitmapFromDrawable(@DrawableRes drawableResId: Int, @ColorRes tintColor: Int = NO_ID): Bitmap {
    val drawable = getDrawable(drawableResId = drawableResId) ?: throw Resources.NotFoundException("The given resource Id does not exist :/")
    return when (drawable) {
        is BitmapDrawable -> drawable.bitmap
        is VectorDrawable -> {
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888).apply {
                val canvas = Canvas(this)
                drawable.setBounds(0, 0, canvas.width, canvas.height)
                if (tintColor != NO_ID) drawable.setTint(getColorInt(tintColor))
                drawable.draw(canvas)
            }
        }
        else -> throw IllegalArgumentException("Unsupported drawable type...")
    }
}

@SuppressLint("UseCompatLoadingForDrawables")
// For API 21 and up the ContextCompat.getDrawable() wrapper just call the same method Context.getDrawable()
fun Context.getDrawable(@DrawableRes drawableResId: Int, theme: Resources.Theme = getTheme()): Drawable? =
    resources.getDrawable(drawableResId, theme)

@ColorInt
fun Context.getColorInt(@ColorRes colorResId: Int, theme: Resources.Theme = getTheme()): Int =
    if (isAtLeastMarshmallow) resources.getColor(colorResId, theme) else ContextCompat.getColor(this, colorResId)

fun Context.getColorStateList(@ColorRes colorResId: Int, theme: Resources.Theme = getTheme()): ColorStateList? =
    if (isAtLeastMarshmallow) resources.getColorStateList(colorResId, theme) else ContextCompat.getColorStateList(this, colorResId)

@ColorInt
fun Context.getColorFromAttr(
    @AttrRes resId: Int, typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): Int {
    typedValue.let {
        theme.resolveAttribute(resId, it, resolveRefs)
        return it.data
    }
}

fun Context.getStringFromAttr(
    @AttrRes resId: Int, typedValue: TypedValue = TypedValue(),
    resolveRefs: Boolean = true
): String {
    typedValue.let {
        theme.resolveAttribute(resId, it, resolveRefs)
        if (it.type == TypedValue.TYPE_STRING && it.string != null) {
            return it.string.toString()
        }
        return EMPTY_STRING
    }
}

fun Context.scanForActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.scanForActivity()
    else -> null
}

fun Context.startActivity(activity: Class<out Activity>) = startActivity(Intent(this, activity))

fun Context.startActivity(intentAction: String = Intent.ACTION_VIEW, uri: Uri) = startActivity(Intent(intentAction, uri))

fun Context.startDialActivity(uri: Uri) = startActivity(Intent.ACTION_DIAL, uri)

fun Context.startApplicationSystemSettingsActivity() {
    startActivity(
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.parse("package:$packageName")
        ).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
}

fun Context.startLocationSettingsActivity() {
    startActivity(
        Intent(
            Settings.ACTION_LOCATION_SOURCE_SETTINGS
        ).apply {
            addCategory(Intent.CATEGORY_DEFAULT)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        })
}

fun Context.startYouTubeActivity(youTubeVideoId: String) {
    try {
        startActivity(uri = Uri.parse("vnd.youtube:$youTubeVideoId"))
    } catch (ex: ActivityNotFoundException) {
        startWebBrowserActivity("https://www.youtube.com/watch?v=$youTubeVideoId")
    }
}

fun Context.startWebBrowserActivity(url: String): Boolean {
    if (TextUtils.isEmpty(url)) {
        return false
    }

    var parsedUrl = url
    if (!url.startsWith("http://") && !url.startsWith("https://")) {
        parsedUrl = "http://$url"
    }

    return try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(parsedUrl)))
        true
    } catch (e: ActivityNotFoundException) {
        false
    }
}

fun Context.startGooglePlayActivity(appPackageName: String = packageName, flags: Int? = null) {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            ).apply { flags?.let { addFlags(it) } })
    } catch (e: ActivityNotFoundException) {
        startWebBrowserActivity("https://play.google.com/store/apps/details?id=$appPackageName")
    }
}

fun Context.showKeyboard(view: View) = inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
fun Context.toggleKeyboard() = inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
fun Context.hideKeyboard(view: View) = inputMethodManager?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

fun Context.showShortToast(@StringRes text: Int) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
fun Context.showLongToast(@StringRes text: Int) = Toast.makeText(this, text, Toast.LENGTH_LONG).show()