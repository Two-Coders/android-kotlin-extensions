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

and much more! Please visit this link for all available common extensions:  
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

    private val viewModel by viewModels<MyFragmentViewModel>()

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

    private val viewModel by viewModels<MyFragmentViewModel>()

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

Use the library by adding `implementation 'com.twocoders.extensions:navigation:1.0.1'` into your build.gradle file.

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

Use the library by adding `implementation 'com.twocoders.extensions:navigation-material:1.0.1'` into your build.gradle file.

### Examples

This library actually only extends the **extensions:navigation** with the ArgsBottomSheetDialogFragment:
```kotlin
// BottomSheetDialogFragment
@AndroidEntryPoint
class MyBottomSheetDialogFragment : ArgsBottomSheetDialogFragment() {

    private val viewModel by viewModels<MyBottomSheetDialogFragmentViewModel>()

    ...
}

// ViewModel
class MyBottomSheetDialogFragmentViewModel @ViewModelInject constructor(
    application: Application,
    @Assisted savedStateHandle: SavedStateHandle
) : ArgsAndroidViewModel(application, savedStateHandle) {

    private val args: MyMyBottomSheetDialogFragmentArgs by navArgs()

    ...
}
```

## Lifecycle Runtime Extensions
This library contains some useful Android Lifecycle Runtime extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-runtime:1.0.0'` into your build.gradle file.

### Examples

LifecycleOwner:
```kotlin
// Obtaining Activity from LifecycleOwner
val activity = lifecycleOwner.activity()

// Check if an Fragment or Activity is in finishing state
val isFinishing: Boolean = lifecycleOwner.isFinishing()
```

## Lifecycle LiveData Extensions
This library contains some useful Android Lifecycle LiveData extensions.

Use the library by adding `implementation 'com.twocoders.extensions:lifecycle-livedata:1.1.0'` into your build.gradle file.

### Examples

This library adds some useful extensions to LiveData and Hadilq LiveEvent:
```kotlin
class MyFragmentViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    val goToSettingsObservable: LiveData<Any> = LiveEvent()
    val requestPermissionsObservable: LiveData<Array<String>> = LiveEvent()

    private fun requestStoragePermission() {
        if (!isStoragePermissionGranted) {
            val permissions = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            requestPermissionsObservable.asLiveEvent().value = permissions
        }
    }

    fun onSettingsButtonClick() {
        goToSettingsObservable.asLiveEvent().call()
    }
}
```

MutableLiveData access restriction:
```kotlin
class MyFragmentViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    val viewAnimatorIndex: LiveData<MainFragmentContentIndex> = MutableLiveData(MainFragmentContentIndex.CONTENT)

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        if (!isStoragePermissionGranted) {
            viewAnimatorIndex.asMutable().value = MainFragmentContentIndex.NO_PERMISSION
        } else {
            switchToContent()
        }
    }
}
```

Transformations:
```kotlin
class MyFragmentViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        val firstLiveData: LiveData<Int> = MutableLiveData(0)
        val secondLiveData: LiveData<String> = MutableLiveData(EMPTY_STRING)

        firstLiveData
            .withLatestFrom(secondLiveData) // Rx withLatestFrom transformation
            .observe(owner) {
                // it is Pair<Int, String>
            }

        firstLiveData
            .zip(secondLiveData) // Rx zip transformation
            .observe(owner) {
                // it is Pair<Int, String>
            }

        firstLiveData
            .combineLatest(secondLiveData) // Rx combineLatest transformation
            .filter { it.first >= 5 } // filter example
            .observe(owner) {
                // it is Pair<Int, String>
            }

        ...
    }
}
```

and much more! Please visit this link for all available LiveData extensions:  
https://github.com/Two-Coders/android-kotlin-extensions/tree/master/extensions/lifecycle-livedata/src/main/java/com/twocoders/extensions/lifecycle/livedata

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

### Examples

This library mostly wrap the common Application extensions:
```kotlin
class MyViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    init {
        ...

        if (hasWifi) { /*true logic here*/ } else { /*false logic here*/ }

        if (isNightModeEnabled) { /*true logic here*/ } else { /*false logic here*/ }

        if (batteryManager.isCharging) { /*true logic here*/ } else { /*false logic here*/ }

        if (checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) { /*true logic here*/ } else { /*false logic here*/ }

        val internalPrivateDirPath = internalPrivateDir.absolutePath

        ...
    }
}
```

and much more! Please visit this link for all available ViewModel extensions:  
https://github.com/Two-Coders/android-kotlin-extensions/tree/master/extensions/lifecycle-viewmodel/src/main/java/com/twocoders/extensions/lifecycle/viewmodel

## Material Extensions
This library contains some useful Android Material extensions.

Use the library by adding `implementation 'com.twocoders.extensions:material:1.0.0'` into your build.gradle file.

### Examples

MaterialAlertDialog:
```kotlin
class MyFragment : Fragment() {

    private val viewModel by viewModels<MyFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            showMaterialAlertDialogObservable.observe(viewLifecycleOwner) {
                showMaterialAlertDialog(it.dialogComponent, buttonClickListener = alertDialogButtonListener)
            }
        }
    }
}
```

BottomSheetDialogFragment Behavior:
```kotlin
class MyBottomSheetDialogFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<MyBottomSheetDialogFragmentViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewModel) {
            setBottomSheetBehaviorObservable.observe(viewLifecycleOwner) { behavior?.state = it }
        }
    }
}
```

## RecyclerView Extensions
This library contains some useful Android RecyclerView extensions.

Use the library by adding `implementation 'com.twocoders.extensions:recyclerview:1.0.0'` into your build.gradle file.

### Examples

ExtendedRecyclerView automatically removes the assigned Adapter in the onDetachedFromWindow:
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto">

    <data>

        <variable
                name="myFragmentViewModel"
                type="com.sample.app.vm.MyFragmentViewModel" />
    </data>

    <com.twocoders.extensions.recyclerview.ExtendedRecyclerView
        android:id="@+id/myRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        ...
        app:adapter="@{myFragmentViewModel.adapter}"
        ... />

</layout>
```

SpanCount extension and BindingAdapter:
```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
                name="myFragmentViewModel"
                type="com.sample.app.vm.MyFragmentViewModel" />
    </data>

    <com.twocoders.extensions.recyclerview.ExtendedRecyclerView
        android:id="@+id/myRecyclerView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        ...
        app:spanCount="@{myFragmentViewModel.spanCount}"
        tools:spanCount="3"
        ... />

</layout>
```
