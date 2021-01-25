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
// This prints: My device is Google Pixel running on Android 10.
Log.i("Example", "My device is $deviceName running on Android $androidVersion.")
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

### Examples

Only Activity specific extensions:
```kotlin
// Data-binding layout binding
class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindLayout(R.layout.my_activity)
    }
}

// Immersive Mode
class MyActivity : AppCompatActivity() {

    private val viewModel by viewModels<MyActivityViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with(viewModel) {
            enterImmersiveModeEvent.observe(this@MyActivity) { enterImmersiveMode() }
            exitImmersiveModeEvent.observe(this@MyActivity) { exitImmersiveMode() }
        }
    }
}
```

and of course all Context extensions as well ;) Please visit this link for all available activity extensions:  
https://github.com/Two-Coders/android-kotlin-extensions/tree/master/extensions/activity/src/main/java/com/twocoders/extensions/activity

## Fragment Extensions
This library contains some useful Android Fragment extensions.

Use the library by adding `implementation 'com.twocoders.extensions:fragment:1.0.0'` into your build.gradle file.

### Examples

Fragment extensions are mostly only wrapped an Activity and Context extensions:
```kotlin
class MyFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            startGooglePlayActivityObservable.observe(viewLifecycleOwner) {
                startGooglePlayActivity()
            }
            startAppSysSettingsActivityObservable.observe(viewLifecycleOwner) {
                startApplicationSystemSettingsActivity()
            }
        }
    }
}
```

Permissions (Multiple Permissions) Launcher:
```kotlin
class MyFragment : Fragment() {

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissionLauncher = registerMultiplePermissionsLauncher {
            viewModel.onPermissionResult(it, requireActivity())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            requestPermissionsObservable.observe(viewLifecycleOwner) { requestPermissionLauncher.launch(it) }
        }
    }
}
```

and of course all Activity and Context extensions as well ;) Please visit this link for all available fragment extensions:  
https://github.com/Two-Coders/android-kotlin-extensions/tree/master/extensions/fragment/src/main/java/com/twocoders/extensions/fragment

## Navigation Extensions
This library contains some useful Android Navigation extensions.

Use the library by adding `implementation 'com.twocoders.extensions:navigation:1.0.0'` into your build.gradle file.

### Examples

Obtaining NavController:
```kotlin
class MyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = bindLayout(R.layout.layout_activity_main)
        val navController = navControllerFrom(R.id.navHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
```

NavArgs with Hilt:
```kotlin
// Fragment (Fragment, DialogFragment and BottomSheetDialogFragment are supported now)
@AndroidEntryPoint
class MyFragment : ArgsFragment() {

    private val viewModel by viewModels<MyFragmentViewModel>()

    ...
}

// ViewModel (AndroidViewModel is supported now)
class MyFragmentViewModel @ViewModelInject constructor(
    application: Application,
    @Assisted savedStateHandle: SavedStateHandle
) : ArgsAndroidViewModel(application, savedStateHandle) {

    private val args: MyFragmentArgs by navArgs()

    ...
}
```

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

### Examples

```kotlin
class MyViewModel @ViewModelInject constructor(
    application: Application,
    preferences: SharedPreferences
) : AndroidViewModel(application) {

    private val preferences = PreferenceManager.getDefaultSharedPreferences(app)

    private val booleanPreferenceLiveData = preferences.liveData("pref-key-boolean", false)
    private val stringPreferenceLiveData = preferences.liveData("pref-key-string", EMPTY_STRING)
    private val enumPreferenceLiveData = preferences.liveData("pref-key-enum", Fruit.APPLE)

    init {
        logd("Boolean preference value is: ${booleanPreferenceLiveData.value}")
        logd("String preference value is: ${stringPreferenceLiveData.value}")
        logd("Enum preference value is: ${enumPreferenceLiveData.value}")
    }

    fun onSomethingHappened() {
        booleanPreferenceLiveData.value = true
        stringPreferenceLiveData.value = "test"
        enumPreferenceLiveData.value = Fruit.BANANA
    }
}
```

## Lifecycle ViewModel Extensions
This library contains some useful Android Lifecycle ViewModel extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-viewmodel:1.0.0'` into your build.gradle file.

## Material Extensions
This library contains some useful Android Material extensions.

Use the library by adding `implementation 'com.twocoders.extensions:material:1.0.0'` into your build.gradle file.

## RecyclerView Extensions
This library contains some useful Android RecyclerView extensions.

Use the library by adding `implementation 'com.twocoders.extensions:recyclerview:1.0.0'` into your build.gradle file.