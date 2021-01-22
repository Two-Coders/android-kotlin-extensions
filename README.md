# Android Kotlin Extensions
This project contains a set of useful Android Kotlin Extensions separated according to their use and dependencies.

## Common Extensions
This library contains some useful common Android extensions without additional dependencies.

Use the library by adding `implementation 'com.twocoders.extensions:common:1.1.0'` into your build.gradle file.

### Examples

Android versions:
```kotlin
if (isAtLeastOreo) { /*true logic here*/ } else { /*false logic here*/ }
```

General information:
```kotlin
// This prints: My device is Google Pixel running Android 10.
Log.i("Example", "My device is $deviceName running Android $androidVersion.")
```

Kotlin Singleton with arguments:
```kotlin
// Declaration
class Repository private constructor(app: Application) {
    companion object : SingletonHolder<Repository, Application>(::Repository)
}

// Access
val repository = Repository.getInstance(app)
```

Context (ApplicationContext) and Application extensions:
```kotlin
// Access system managers
val inputMethodManager: InputMethodManager? = context.inputMethodManager // or application.inputMethodManager
val batteryManager: BatteryManager? = context.batteryManager // or application.batteryManager
...

// Check device features
val hasGps: Boolean = context.hasGps // or application.hasGps
val hasGyroscope: Boolean = context.hasGyroscope // or application.hasGyroscope
...

// Check system features
val isRtl: Boolean = context.isRtl // or application.isRtl
val isNightModeEnabled: Boolean = context.isNightModeEnabled // or application.isNightModeEnabled
val isNetworkAvailable: Boolean = context.isNetworkAvailable // or application.isNetworkAvailable
...

// Start most common activities
context.startApplicationSystemSettingsActivity()
context.startYouTubeActivity("Rqfufnlq_KU")
context.startWebBrowserActivity("https://github.com/")
context.startGooglePlayActivity("com.google.android.keep")
...

// Soft Keyboard
context.showKeyboard(view)
context.toggleKeyboard()
context.hideKeyboard(view)

// Toasts
context.showShortToast(R.string.test)
context.showLongToast(R.string.test)

```

and much more. Please visit this link for all available common extensions:
https://github.com/Two-Coders/android-kotlin-extensions/tree/master/extensions/common/src/main/java/com/twocoders/extensions/common

## Activity Extensions
This library contains some useful Android Activity extensions.

Use the library by adding `implementation 'com.twocoders.extensions:activity:1.0.0'` into your build.gradle file.

## Fragment Extensions
This library contains some useful Android Fragment extensions.

Use the library by adding `implementation 'com.twocoders.extensions:fragment:1.0.0'` into your build.gradle file.

## Navigation Extensions
This library contains some useful Android Navigation extensions.

Use the library by adding `implementation 'com.twocoders.extensions:navigation:1.0.0'` into your build.gradle file.

## Navigation Material Extensions
This library contains some useful Android Navigation Material extensions.

Use the library by adding `implementation 'com.twocoders.extensions:navigation-material:1.0.0'` into your build.gradle file.

## Lifecycle Runtime Extensions
This library contains some useful Android Lifecycle Runtime extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-runtime:1.0.0'` into your build.gradle file.

## Lifecycle LiveData Extensions
This library contains some useful Android Lifecycle LiveData extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-livedata:1.1.0'` into your build.gradle file.

## Lifecycle LiveData Preference Extensions
This library contains some useful Android Lifecycle LiveData Preference extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-livedata-preference:1.0.0'` into your build.gradle file.

## Lifecycle ViewModel Extensions
This library contains some useful Android Lifecycle ViewModel extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-viewmodel:1.0.0'` into your build.gradle file.

## Material Extensions
This library contains some useful Android Material extensions.

Use the library by adding `implementation 'com.twocoders.extensions:material:1.0.0'` into your build.gradle file.

## RecyclerView Extensions
This library contains some useful Android RecyclerView extensions.

Use the library by adding `implementation 'com.twocoders.extensions:recyclerview:1.0.0'` into your build.gradle file.