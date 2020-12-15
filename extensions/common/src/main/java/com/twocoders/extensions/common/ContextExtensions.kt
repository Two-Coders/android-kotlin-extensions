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
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Surface
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.*
import androidx.core.content.ContextCompat

inline val Context.audioManager: AudioManager?
    get() = applicationContext.getSystemService(AUDIO_SERVICE) as? AudioManager

inline val Context.locationManager: LocationManager?
    get() = applicationContext.getSystemService(LOCATION_SERVICE) as? LocationManager

inline val Context.clipboardManager: ClipboardManager?
    get() = applicationContext.getSystemService(CLIPBOARD_SERVICE) as? ClipboardManager

inline val Context.inputMethodManager: InputMethodManager?
    get() = applicationContext.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager

inline val Context.notificationManager: NotificationManager?
    get() = applicationContext.getSystemService(NOTIFICATION_SERVICE) as? NotificationManager

inline val Context.connectivityManager: ConnectivityManager?
    get() = applicationContext.getSystemService(CONNECTIVITY_SERVICE) as? ConnectivityManager

inline val Context.networkStatsManager: NetworkStatsManager?
    @RequiresApi(api = Build.VERSION_CODES.M)
    get() = applicationContext.getSystemService(NETWORK_STATS_SERVICE) as? NetworkStatsManager

inline val Context.wifiManager: WifiManager?
    get() = applicationContext.getSystemService(WIFI_SERVICE) as? WifiManager

inline val Context.windowManager: WindowManager?
    get() = applicationContext.getSystemService(WINDOW_SERVICE) as? WindowManager

inline val Context.activityManager: ActivityManager?
    get() = applicationContext.getSystemService(ACTIVITY_SERVICE) as? ActivityManager

inline val Context.camera2Manager: CameraManager?
    get() = applicationContext.getSystemService(CAMERA_SERVICE) as? CameraManager

inline val Context.sensorManager: SensorManager?
    get() = applicationContext.getSystemService(SENSOR_SERVICE) as? SensorManager

inline val Context.powerManager: PowerManager?
    get() = applicationContext.getSystemService(POWER_SERVICE) as? PowerManager

inline val Context.alarmManager: AlarmManager?
    get() = applicationContext.getSystemService(ALARM_SERVICE) as? AlarmManager

inline val Context.keyguardManager: KeyguardManager?
    get() = applicationContext.getSystemService(KEYGUARD_SERVICE) as? KeyguardManager

inline val Context.searchManager: SearchManager?
    get() = applicationContext.getSystemService(SEARCH_SERVICE) as? SearchManager

inline val Context.storageManager: StorageManager?
    get() = applicationContext.getSystemService(STORAGE_SERVICE) as? StorageManager

inline val Context.vibratorManager: Vibrator?
    get() = applicationContext.getSystemService(VIBRATOR_SERVICE) as? Vibrator

inline val Context.telephonyManager: TelephonyManager?
    get() = applicationContext.getSystemService(TELEPHONY_SERVICE) as? TelephonyManager

inline val Context.uiModeManager: UiModeManager?
    get() = applicationContext.getSystemService(UI_MODE_SERVICE) as? UiModeManager

inline val Context.downloadManager: DownloadManager?
    get() = applicationContext.getSystemService(DOWNLOAD_SERVICE) as? DownloadManager

inline val Context.batteryManager: BatteryManager?
    get() = applicationContext.getSystemService(BATTERY_SERVICE) as? BatteryManager

inline val Context.hardwarePropertiesManager: HardwarePropertiesManager?
    @RequiresApi(api = Build.VERSION_CODES.N)
    get() = applicationContext.getSystemService(HARDWARE_PROPERTIES_SERVICE) as? HardwarePropertiesManager

fun Context.isNetworkAvailable(): Boolean = connectivityManager?.let {
    if (isAtLeastMarshmallow()) {
        it.getNetworkCapabilities(it.activeNetwork)?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    } else {
        @Suppress("DEPRECATION")
        it.activeNetworkInfo?.isConnected
    }
} ?: false

fun Context.isNetworkUnavailable() = !isNetworkAvailable()

fun Context.scanForActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.scanForActivity()
    else -> null
}

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
    if (isAtLeastMarshmallow()) resources.getColor(colorResId, theme) else ContextCompat.getColor(this, colorResId)

fun Context.getColorStateList(@ColorRes colorResId: Int, theme: Resources.Theme = getTheme()): ColorStateList? =
    if (isAtLeastMarshmallow()) resources.getColorStateList(colorResId, theme) else ContextCompat.getColorStateList(this, colorResId)

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

fun Context.startActivity(intentAction: String = Intent.ACTION_VIEW, uri: Uri) {
    startActivity(Intent(intentAction, uri))
}

fun Context.startGooglePlayActivity(appPackageName: String = packageName, flags: Int? = null) {
    try {
        startActivity(
            Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackageName")).apply {
                flags?.let { addFlags(it) }
            })
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            ).apply {
                flags?.let { addFlags(it) }
            })
    }
}

fun Context.startDialActivity(uri: Uri) {
    startActivity(Intent.ACTION_DIAL, uri)
}

fun Context.startApplicationDetailsSettingsActivity() {
    startActivity(Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
        data = Uri.fromParts("package", packageName, null)
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}

fun Context.startLocationSettingsActivity() {
    startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).apply {
        addCategory(Intent.CATEGORY_DEFAULT)
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    })
}

fun Context.isRtl(): Boolean {
    return this.resources.configuration.layoutDirection == View.LAYOUT_DIRECTION_RTL
}

fun Context.isNightModeEnabled() = when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
    Configuration.UI_MODE_NIGHT_YES -> true
    Configuration.UI_MODE_NIGHT_NO, Configuration.UI_MODE_NIGHT_UNDEFINED -> false
    else -> false
}

val Context.displayRotation: Int
    get() = (if (isAtLeastR()) {
        display
    } else {
        @Suppress("DEPRECATION")
        windowManager?.defaultDisplay
    })?.rotation ?: Surface.ROTATION_0

val Context.layoutInflater: LayoutInflater
    get() = LayoutInflater.from(this)

fun Context.checkPermission(permission: String) = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Context.checkPermissions(permissions: Array<String>): Map<String, Boolean> = permissions.associateBy({ it }, { checkPermission(it) })

fun Context.hasPermissionInManifest(permissionName: String) = try {
    packageManager
        .getPackageInfo(packageName, PackageManager.GET_PERMISSIONS)
        .requestedPermissions?.contains(permissionName) ?: false
} catch (e: PackageManager.NameNotFoundException) {
    false
}

fun Context.internalPrivateDir() = filesDir
fun Context.internalPrivateCacheDir() = cacheDir
fun Context.externalPrivateDir(subDir: String? = null) = getExternalFilesDir(subDir)
fun Context.externalPrivateCacheDir() = externalCacheDir
fun Context.externalPrivateDirs(subDir: String? = null) = getExternalFilesDirs(subDir)
fun Context.externalPrivateCacheDirs() = externalCacheDirs

fun Context.showKeyboard(view: View) = inputMethodManager?.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
fun Context.toggleKeyboard() = inputMethodManager?.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
fun Context.hideKeyboard(view: View) = inputMethodManager?.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

fun Context.applicationName(): String {
    val stringId: Int = applicationInfo.labelRes
    return if (stringId == 0) applicationInfo.nonLocalizedLabel.toString() else getString(stringId)
}