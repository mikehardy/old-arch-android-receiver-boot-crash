# old-arch-android-receiver-boot-crash

![Build](https://github.com/mikehardy/old-arch-android-receiver-boot-crash/workflows/Pre%20Merge%20Checks/badge.svg)

This is your new React Native Reproducer project.

# Reproducer TODO list

- [x] 1. Create a new reproducer project.
- [x] 2. Git clone your repository locally.
- [x] 3. Edit the project to reproduce the failure you're seeing.
- [x] 4. Push your changes, so that Github Actions can run the CI.
- [x] 5. Make sure the repository is public and share the link with the issue you reported.

# How to use this Reproducer

Perform these steps to reproduce the error in [facebook/react-native#47570](https://github.com/facebook/react-native/issues/47570)

1. `cd ReproducerApp`
1. `yarn`
1. Start an Android emulator but an older one before background receivers couldn't start background services. I tested this on an API24 (Android...7?) emulator. It is not worth the pain setting up all the other stuff when the error reproduces perfectly
1. `yarn run-android --mode release` (the app should open in minified release mode, and a metro server will open)
1. `adb logcat` in some separate terminal
1. open the task switcher on the android emulator and swipe the reproducer app away
1. In a terminal, directly send an intent that will trigger a receiver and start a HeadlessJS service: `adb shell am broadcast   -n com.reproducerapp/.ExampleReceiver   -a "com.example.intent.TestIntent"`
1. You should see the following in your logs indicating some problem loading libraries etc:

<details>

```11-25 00:02:14.307  3640  3640 D AndroidRuntime: >>>>>> START com.android.internal.os.RuntimeInit uid 2000 <<<<<<
11-25 00:02:14.308  3640  3640 D AndroidRuntime: CheckJNI is OFF
11-25 00:02:14.308  3640  3640 E cutils-trace: Error opening trace file: No such file or directory (2)
11-25 00:02:14.315  3640  3640 D ICU     : No timezone override file found: /data/misc/zoneinfo/current/icu/icu_tzdata.dat
11-25 00:02:14.318  3640  3640 E memtrack: Couldn't load memtrack module (No such file or directory)
11-25 00:02:14.318  3640  3640 E android.os.Debug: failed to load memtrack module: -2
11-25 00:02:14.318  3640  3640 I Radio-JNI: register_android_hardware_Radio DONE
11-25 00:02:14.320  3640  3640 D AndroidRuntime: Calling main entry com.android.commands.am.Am
11-25 00:02:14.333  1181  1195 I ActivityManager: Start proc 3650:com.reproducerapp/u0a79 for broadcast com.reproducerapp/.ExampleReceiver
11-25 00:02:14.338  3650  3650 D WM-WrkMgrInitializer: Initializing WorkManager with default configuration.
11-25 00:02:14.340  3650  3650 D NOTIFEE : (context): received application context
11-25 00:02:14.341  3650  3650 W SoLoader: Initializing SoLoader: 0
11-25 00:02:14.341  3650  3650 W SoLoader: Recording new base apk path: /data/app/com.reproducerapp-1/base.apk
11-25 00:02:14.341  3650  3650 W SoLoader: Previously recorded 0 base apk paths.
11-25 00:02:14.341  3650  3650 I SoLoader: Preparing SO source: DirectorySoSource[root = /system/vendor/lib64 flags = 2]
11-25 00:02:14.341  3650  3650 I SoLoader: Preparing SO source: DirectorySoSource[root = /system/lib64 flags = 2]
11-25 00:02:14.341  3650  3650 I SoLoader: Preparing SO source: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-25 00:02:14.342  3650  3650 I SoLoader: Preparing SO source: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-25 00:02:14.343  3650  3650 I SoLoader: init finish: 4 SO sources prepared
11-25 00:02:14.343  3650  3650 W SoLoader: SoLoader initialized: 8
11-25 00:02:14.344  3650  3650 W linker  : /data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a/libc++_shared.so: unused DT entry: type 0x70000001 arg 0x0
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Entering merged library JNI_OnLoad.
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing 12 pre-merged libs (including stub)
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libfabricjni_so.  onload_func: 0x70950ea984
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libmapbufferjni_so.  onload_func: 0x7095135c6c
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libreact_devsupportjni_so.  onload_func: 0x70951ad3d0
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libreact_featureflagsjni_so.  onload_func: 0x70951b4530
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libreact_newarchdefaults_so.  onload_func: 0x70951d7804
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libreactnativeblob_so.  onload_func: 0x709525f93c
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libreactnativejni_so.  onload_func: 0x709527e284
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register librninstance_so.  onload_func: 0x70952983a8
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libturbomodulejsijni_so.  onload_func: 0x70952f7578
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libuimanagerjni_so.  onload_func: 0x70952fedf0
11-25 00:02:14.349  3650  3650 D jni_lib_merge: Preparing to register libyoga_so.  onload_func: 0x70953040a0
11-25 00:02:14.349  3650  3650 D jni_lib_merge: About to register 11 actual methods.
11-25 00:02:14.349  3650  3650 D REPRODUCER: we received an intent. Starting ExampleService...
11-25 00:02:14.350  3650  3650 D REPRODUCER: Successfully started ExampleService.
11-25 00:02:14.350  3650  3650 D REPRODUCER: ExampleService::getTaskConfig
11-25 00:02:14.351  3640  3640 D AndroidRuntime: Shutting down VM
11-25 00:02:14.353  3650  3650 D jni_lib_merge: Entering merged library JNI_OnLoad.
11-25 00:02:14.353  3650  3650 D jni_lib_merge: Preparing 4 pre-merged libs (including stub)
11-25 00:02:14.353  3650  3650 D jni_lib_merge: Preparing to register libhermes_executor_so.  onload_func: 0x70a6a1b780
11-25 00:02:14.353  3650  3650 D jni_lib_merge: Preparing to register libhermesinstancejni_so.  onload_func: 0x70a6a22178
11-25 00:02:14.353  3650  3650 D jni_lib_merge: Preparing to register libjsijniprofiler_so.  onload_func: 0x70a6a22834
11-25 00:02:14.353  3650  3650 D jni_lib_merge: About to register 3 actual methods.
11-25 00:02:14.353  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateStartTask(): Schedule
11-25 00:02:14.353  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask()
11-25 00:02:14.353  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Start
11-25 00:02:14.353  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getJSBundleLoader()
11-25 00:02:14.353  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactContext(): Creating BridgelessReactContext
11-25 00:02:14.353  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Creating ReactInstance
11-25 00:02:14.360  3650  3669 D NetworkSecurityConfig: No Network Security Config specified, using platform default
11-25 00:02:14.361  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Loading JS Bundle
11-25 00:02:14.366  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.progressbar.ReactProgressBarViewManager
11-25 00:02:14.367  3650  3669 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Calling DevSupportManagerBase.onNewReactContextCreated(reactContext)
11-25 00:02:14.367  3650  3650 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Executing ReactInstanceEventListeners
11-25 00:02:14.368  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.progressbar.b
11-25 00:02:14.369  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.ReactVirtualTextViewManager
11-25 00:02:14.370  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.o
11-25 00:02:14.371  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.scroll.ReactHorizontalScrollContainerViewManager
11-25 00:02:14.371  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.uimanager.V
11-25 00:02:14.372  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.scroll.ReactScrollViewManager
11-25 00:02:14.373  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.frescosupport.FrescoBasedReactTextInlineImageViewManager
11-25 00:02:14.373  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.frescosupport.a
11-25 00:02:14.374  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.unimplementedview.ReactUnimplementedViewManager
11-25 00:02:14.375  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.swiperefresh.SwipeRefreshLayoutManager
11-25 00:02:14.376  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.ReactTextViewManager
11-25 00:02:14.379  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.g
11-25 00:02:14.381  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.textinput.ReactTextInputManager
11-25 00:02:14.382  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.textinput.H
11-25 00:02:14.382  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.modal.ReactModalHostManager
11-25 00:02:14.383  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.switchview.ReactSwitchManager
11-25 00:02:14.383  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.switchview.ReactSwitchManager$b
11-25 00:02:14.384  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.ReactRawTextManager
11-25 00:02:14.384  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.text.d
11-25 00:02:14.384  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.safeareaview.ReactSafeAreaViewManager
11-25 00:02:14.384  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.safeareaview.c
11-25 00:02:14.384  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.scroll.ReactHorizontalScrollViewManager
11-25 00:02:14.385  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.view.ReactViewManager
11-25 00:02:14.386  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.image.ReactImageManager
11-25 00:02:14.387  3650  3670 W unknown:ViewManagerPropertyUpdater: Could not find generated setter for class com.facebook.react.views.drawer.ReactDrawerLayoutManager
11-25 00:02:14.388  3650  3670 I ReactNativeJS: Bridgeless mode is enabled
11-25 00:02:14.389  3650  3670 I ReactNativeJS: ExampleHeadlessTask JS executing. Received taskData: {"foo":"bar"}
11-25 00:02:14.389  3650  3670 I ReactNativeJS: This is running from ExampleHeadlessTask::setImmediate
11-25 00:02:14.391  1181  1181 D ZenLog  : intercepted: 0|com.reproducerapp|1834285169|null|10079,alarmsOnly
11-25 00:02:14.391  1181  1181 V NotificationService: pkg=com.reproducerapp canInterrupt=false intercept=true
11-25 00:02:14.391  3650  3650 D getReactContext: New Architecture class load failed. Using fallback.
11-25 00:02:14.391  3650  3650 D getReactContext: Determining ReactContext using fallback method
11-25 00:02:14.392  3650  3650 W SoLoader: SoLoader already initialized
11-25 00:02:14.392  3650  3650 W SoLoader: SoLoader already initialized
11-25 00:02:14.510  3650  3650 D getReactContext: New Architecture class load failed. Using fallback.
11-25 00:02:14.510  3650  3650 D getReactContext: Determining ReactContext using fallback method
11-25 00:02:14.512  3650  3678 E ReactNativeJS: Error: Exception in HostFunction: Could not enqueue microtask because they are disabled in this runtime, js engine: hermes
11-25 00:02:14.513  3650  3679 E AndroidRuntime: FATAL EXCEPTION: mqt_native_modules
11-25 00:02:14.513  3650  3679 E AndroidRuntime: Process: com.reproducerapp, PID: 3650
11-25 00:02:14.513  3650  3679 E AndroidRuntime: com.facebook.react.common.JavascriptException: Error: Exception in HostFunction: Could not enqueue microtask because they are disabled in this runtime, js engine: hermes, stack:
11-25 00:02:14.513  3650  3679 E AndroidRuntime: setImmediate@1:177996
11-25 00:02:14.513  3650  3679 E AndroidRuntime: startHeadlessTask@1:233022
11-25 00:02:14.513  3650  3679 E AndroidRuntime: __callFunction@1:30941
11-25 00:02:14.513  3650  3679 E AndroidRuntime: anonymous@1:29246
11-25 00:02:14.513  3650  3679 E AndroidRuntime: __guard@1:30202
11-25 00:02:14.513  3650  3679 E AndroidRuntime: callFunctionReturnFlushedQueue@1:29204
11-25 00:02:14.513  3650  3679 E AndroidRuntime: 
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at com.facebook.react.modules.core.ExceptionsManagerModule.reportException(SourceFile:77)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at com.facebook.jni.NativeRunnable.run(Native Method)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at android.os.Handler.handleCallback(Handler.java:751)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at android.os.Handler.dispatchMessage(Handler.java:95)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(SourceFile:1)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at android.os.Looper.loop(Looper.java:154)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at com.facebook.react.bridge.queue.MessageQueueThreadImpl.lambda$startNewBackgroundThread$2(SourceFile:36)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at com.facebook.react.bridge.queue.MessageQueueThreadImpl.b(SourceFile:1)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at com.facebook.react.bridge.queue.b.run(SourceFile:1)
11-25 00:02:14.513  3650  3679 E AndroidRuntime:  at java.lang.Thread.run(Thread.java:761)
11-25 00:02:14.514  1181  3485 W ActivityManager: Process com.reproducerapp has crashed too many times: killing!
11-25 00:02:14.514  1181  3485 I ActivityManager: Killing 3650:com.reproducerapp/u0a79 (adj 500): crash
11-25 00:02:14.514  1181  3485 D ActivityManager: cleanUpApplicationRecord -- 3650

```

</details>

If you rebuild the app but with `enableProguardInReleaseBuilds = false` in `android/app/build.gradle`, it will work and you will see the headless JS console output in metro indicating it worked.
