# old-arch-android-receiver-boot-crash

![Build](https://github.com/mikehardy/old-arch-android-receiver-boot-crash/workflows/Pre%20Merge%20Checks/badge.svg)

This is your new React Native Reproducer project.

# Reproducer TODO list

- [x] 1. Create a new reproducer project.
- [ ] 2. Git clone your repository locally.
- [ ] 3. Edit the project to reproduce the failure you're seeing.
- [ ] 4. Push your changes, so that Github Actions can run the CI.
- [ ] 5. Make sure the repository is public and share the link with the issue you reported.

# How to use this Reproducer

Perform these steps to reproduce the error in [facebook/react-native#47592](https://github.com/facebook/react-native/issues/47592)

1. `cd ReproducerApp`
1. `yarn`
1. Start an Android emulator but an older one before background receivers couldn't start background services. I tested this on an API24 (Android...7?) emulator. It is not worth the pain setting up all the other stuff when the error reproduces perfectly
1. `yarn react-native run-android` (the app should open, and a mertro server will open)
1. `adb logcat` in some separate terminal
1. open the task switcher on the android emulator and swipe the reproducer app away
1. In a terminal, directly send an intent that will trigger a receiver and start a HeadlessJS service: `adb shell am broadcast   -n com.reproducerapp/.ExampleReceiver   -a "com.example.intent.TestIntent"`
1. You should see the following in your logs indicating some problem loading libraries etc:

<details>

```
11-17 17:03:14.432  4366  4366 D AndroidRuntime: Calling main entry com.android.commands.am.Am
11-17 17:03:14.439  4376  4376 I art     : Late-enabling -Xcheck:jni
11-17 17:03:14.446  1227  1241 I ActivityManager: Start proc 4376:com.reproducerapp/u0a75 for broadcast com.reproducerapp/.ExampleReceiver
11-17 17:03:14.456  4376  4376 W SoLoader: Initializing SoLoader: 0
11-17 17:03:14.456  4376  4376 W SoLoader: Recording new base apk path: /data/app/com.reproducerapp-1/base.apk
11-17 17:03:14.456  4376  4376 W SoLoader: Previously recorded 0 base apk paths.
11-17 17:03:14.458  4376  4376 I SoLoader: Preparing SO source: DirectorySoSource[root = /system/vendor/lib64 flags = 2]
11-17 17:03:14.458  4376  4376 I SoLoader: Preparing SO source: DirectorySoSource[root = /system/lib64 flags = 2]
11-17 17:03:14.458  4376  4376 I SoLoader: Preparing SO source: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.463  4376  4376 I SoLoader: Preparing SO source: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.463  4376  4376 I SoLoader: init finish: 4 SO sources prepared
11-17 17:03:14.463  4376  4376 W SoLoader: SoLoader initialized: 8
11-17 17:03:14.463  4376  4376 D REPRODUCER: we received an intent. Starting ExampleService...
11-17 17:03:14.464  4376  4376 D REPRODUCER: Successfully started ExampleService.
11-17 17:03:14.465  4366  4366 D AndroidRuntime: Shutting down VM
11-17 17:03:14.466  4376  4376 D REPRODUCER: ExampleService::getTaskConfig
11-17 17:03:14.470  4376  4376 W linker  : /data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a/libc++_shared.so: unused DT entry: type 0x70000001 arg 0x0
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Entering merged library JNI_OnLoad.
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing 12 pre-merged libs (including stub)
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libfabricjni_so.  onload_func: 0x7e78333b98
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libmapbufferjni_so.  onload_func: 0x7e7840f5fc
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libreact_devsupportjni_so.  onload_func: 0x7e78561034
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libreact_featureflagsjni_so.  onload_func: 0x7e7856d544
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libreact_newarchdefaults_so.  onload_func: 0x7e785de000
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libreactnativeblob_so.  onload_func: 0x7e787e0ff4
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libreactnativejni_so.  onload_func: 0x7e7883d130
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register librninstance_so.  onload_func: 0x7e7888a208
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libturbomodulejsijni_so.  onload_func: 0x7e789444b0
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libuimanagerjni_so.  onload_func: 0x7e7895ed7c
11-17 17:03:14.484  4376  4376 D jni_lib_merge: Preparing to register libyoga_so.  onload_func: 0x7e7896cf9c
11-17 17:03:14.484  4376  4376 D jni_lib_merge: About to register 11 actual methods.
11-17 17:03:14.486  4376  4376 W SoLoader: SoLoader already initialized
11-17 17:03:14.492  4376  4376 D jni_lib_merge: Entering merged library JNI_OnLoad.
11-17 17:03:14.492  4376  4376 D jni_lib_merge: Preparing 4 pre-merged libs (including stub)
11-17 17:03:14.492  4376  4376 D jni_lib_merge: Preparing to register libhermes_executor_so.  onload_func: 0x7e78da4e88
11-17 17:03:14.492  4376  4376 D jni_lib_merge: Preparing to register libhermesinstancejni_so.  onload_func: 0x7e78dd85c0
11-17 17:03:14.492  4376  4376 D jni_lib_merge: Preparing to register libjsijniprofiler_so.  onload_func: 0x7e78dda778
11-17 17:03:14.492  4376  4376 D jni_lib_merge: About to register 3 actual methods.
11-17 17:03:14.492  4376  4376 W SoLoader: SoLoader already initialized
11-17 17:03:14.496  4376  4376 D NetworkSecurityConfig: No Network Security Config specified, using platform default
11-17 17:03:14.499  4376  4376 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.499  4376  4376 W SoLoader:  existing SO sources: 
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 2]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 2]
11-17 17:03:14.499  4376  4376 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.499  4376  4376 W soloader.recovery.CheckBaseApkExists: Base apk exists: /data/app/com.reproducerapp-1/base.apk
11-17 17:03:14.499  4376  4376 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.499  4376  4376 W SoLoader:  existing SO sources: 
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 2]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 2]
11-17 17:03:14.499  4376  4376 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.499  4376  4376 E SoLoader: Checking /data/app missing libraries.
11-17 17:03:14.499  4376  4376 E SoLoader: Successfully recovered from /data/app disk failure.
11-17 17:03:14.499  4376  4376 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.499  4376  4376 W SoLoader:  existing SO sources: 
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 3]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 3]
11-17 17:03:14.499  4376  4376 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.499  4376  4376 E SoLoader: Checking /data/data missing libraries.
11-17 17:03:14.499  4376  4376 E SoLoader: No libraries missing from unpacking so paths while recovering /data/data failure
11-17 17:03:14.499  4376  4376 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.499  4376  4376 W SoLoader:  existing SO sources: 
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 3]
11-17 17:03:14.499  4376  4376 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 3]
11-17 17:03:14.499  4376  4376 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.499  4376  4376 W SoLoader: Failed to recover
11-17 17:03:14.505  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateStartTask(): Schedule
11-17 17:03:14.505  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask()
11-17 17:03:14.505  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Start
11-17 17:03:14.505  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getJSBundleLoader()
11-17 17:03:14.505  4376  4399 W unknown:BridgelessReact: ReactHost{0}.isMetroRunning()
11-17 17:03:14.514  4376  4404 W unknown:BridgelessReact: ReactHost{0}.isMetroRunning(): Async result = true
11-17 17:03:14.514  4376  4399 W unknown:BridgelessReact: ReactHost{0}.loadJSBundleFromMetro()
11-17 17:03:14.515  4376  4376 E unknown:ReactNative: Unable to display loading message because react activity isn't available
11-17 17:03:14.677  4376  4404 W unknown:BridgelessReact: ReactHost{0}.loadJSBundleFromMetro(): Creating BundleLoader
11-17 17:03:14.678  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactContext(): Creating BridgelessReactContext
11-17 17:03:14.678  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Creating ReactInstance
11-17 17:03:14.683  4376  4399 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.683  4376  4399 W SoLoader:  existing SO sources: 
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.683  4376  4399 W soloader.recovery.CheckBaseApkExists: Base apk exists: /data/app/com.reproducerapp-1/base.apk
11-17 17:03:14.683  4376  4399 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.683  4376  4399 W SoLoader:  existing SO sources: 
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.683  4376  4399 E SoLoader: Checking /data/app missing libraries.
11-17 17:03:14.683  4376  4399 E SoLoader: Successfully recovered from /data/app disk failure.
11-17 17:03:14.683  4376  4399 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.683  4376  4399 W SoLoader:  existing SO sources: 
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.683  4376  4399 E SoLoader: Checking /data/data missing libraries.
11-17 17:03:14.683  4376  4399 E SoLoader: No libraries missing from unpacking so paths while recovering /data/data failure
11-17 17:03:14.683  4376  4399 W SoLoader: Running a recovery step for libappmodules.so due to com.facebook.soloader.SoLoaderDSONotFoundError: couldn't find DSO to load: libappmodules.so
11-17 17:03:14.683  4376  4399 W SoLoader:  existing SO sources: 
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 0: ApplicationSoSource[DirectorySoSource[root = /data/app/com.reproducerapp-1/lib/arm64 flags = 0]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 1: DirectApkSoSource[root = [/data/app/com.reproducerapp-1/base.apk!/lib/arm64-v8a]]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 2: DirectorySoSource[root = /system/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:   SoSource 3: DirectorySoSource[root = /system/vendor/lib64 flags = 3]
11-17 17:03:14.683  4376  4399 W SoLoader:  Native lib dir: /data/app/com.reproducerapp-1/lib/arm64
11-17 17:03:14.683  4376  4399 W SoLoader: Failed to recover
11-17 17:03:14.686  4376  4399 W ReactNativeJNI: Custom component descriptors were not configured from JNI_OnLoad
11-17 17:03:14.694  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Loading JS Bundle
11-17 17:03:14.720  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Calling DevSupportManagerBase.onNewReactContextCreated(reactContext)
11-17 17:03:14.721  4376  4376 W unknown:ReactNative: Packager connection already open, nooping.
11-17 17:03:14.721  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateReactInstanceTask(): Executing ReactInstanceEventListeners
11-17 17:03:14.752  4376  4412 E ReactNativeJS: Invariant Violation: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary.
11-17 17:03:14.753  4376  4412 I ReactNativeJS: 'Failed to print error: ', 'TurboModuleRegistry.getEnforcing(...): \'PlatformConstants\' could not be found. Verify that a module by this name is registered in the native binary.'
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: ReactHost{0}.handleHostException(message = "EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: invariant@2009:26
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: getEnforcing@2703:28
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@2671:68
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@2647:76
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@2606:75
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@22930:53
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@25952:51
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@25938:200
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: anonymous@21190:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: global@104546:4
11-17 17:03:14.828  4376  4412 W unknown:BridgelessReact: ")
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: Exception in native call from JS
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: com.facebook.react.common.JavascriptException: EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: invariant@2009:26
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: getEnforcing@2703:28
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@2671:68
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@2647:76
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@2606:75
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@22930:53
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@25952:51
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@25938:200
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: anonymous@21190:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: global@104546:4
11-17 17:03:14.828  4376  4412 E unknown:ReactNative: 
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at com.facebook.react.modules.core.ExceptionsManagerModule.reportException(ExceptionsManagerModule.kt:52)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at com.facebook.react.runtime.ReactInstance$ReactJsExceptionHandlerImpl.reportJsException(ReactInstance.java:336)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at com.facebook.jni.NativeRunnable.run(Native Method)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at android.os.Handler.handleCallback(Handler.java:751)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at android.os.Handler.dispatchMessage(Handler.java:95)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:27)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at android.os.Looper.loop(Looper.java:154)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at com.facebook.react.bridge.queue.MessageQueueThreadImpl.lambda$startNewBackgroundThread$2(MessageQueueThreadImpl.java:217)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at com.facebook.react.bridge.queue.MessageQueueThreadImpl$$ExternalSyntheticLambda1.run(D8$$SyntheticClass:0)
11-17 17:03:14.828  4376  4412 E unknown:ReactNative:  at java.lang.Thread.run(Thread.java:761)
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask()
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: ReactHost{0}.raiseSoftException(getOrCreateDestroyTask()): handleHostException(message = "EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: invariant@2009:26
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: getEnforcing@2703:28
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@2671:68
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@2647:76
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@2606:75
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@22930:53
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@25952:51
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@25938:200
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: anonymous@21190:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: metroRequire@89:92
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: global@104546:4
11-17 17:03:14.828  4376  4399 W unknown:BridgelessReact: ")
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: Unable to launch redbox because react activity is not available, here is the error that redbox would've displayed: EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: invariant@2009:26
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: getEnforcing@2703:28
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@2671:68
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@2647:76
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@2606:75
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@22930:53
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@25952:51
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@25938:200
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@21190:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: global@104546:4
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: Unable to launch redbox because react activity is not available, here is the error that redbox would've displayed: EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: invariant@2009:26
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: getEnforcing@2703:28
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@2671:68
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@2647:76
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@2606:75
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@22930:53
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@25952:51
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@25938:200
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: anonymous@21190:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: metroRequire@89:92
11-17 17:03:14.828  4376  4376 E unknown:ReactNative: global@104546:4
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: Unhandled SoftException
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: com.facebook.react.bridge.ReactNoCrashSoftException: raiseSoftException(getOrCreateDestroyTask()): handleHostException(message = "EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: invariant@2009:26
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: getEnforcing@2703:28
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@2671:68
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@2647:76
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@2606:75
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@22930:53
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@25952:51
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@25938:200
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@21190:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: global@104546:4
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: ")
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.ReactHostImpl.raiseSoftException(ReactHostImpl.java:942)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.ReactHostImpl.getOrCreateDestroyTask(ReactHostImpl.java:1575)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.ReactHostImpl.lambda$destroy$7(ReactHostImpl.java:541)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.ReactHostImpl.$r8$lambda$uso21_D6dCZdcf-JomVD56kdG4c(ReactHostImpl.java)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.ReactHostImpl$$ExternalSyntheticLambda37.call(D8$$SyntheticClass:0)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.internal.bolts.Task$2.run(Task.java:240)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1133)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:607)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at java.lang.Thread.run(Thread.java:761)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: Caused by: com.facebook.react.common.JavascriptException: EarlyJsError: TurboModuleRegistry.getEnforcing(...): 'PlatformConstants' could not be found. Verify that a module by this name is registered in the native binary., stack:
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: invariant@2009:26
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: getEnforcing@2703:28
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@2671:68
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@2647:76
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@2606:75
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@22930:53
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@25952:51
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@25938:200
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@175:38
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: anonymous@21190:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: loadModuleImplementation@267:14
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: guardedLoadModule@168:47
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: metroRequire@89:92
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: global@104546:4
11-17 17:03:14.828  4376  4399 E unknown:ReactHost: 
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.modules.core.ExceptionsManagerModule.reportException(ExceptionsManagerModule.kt:52)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.runtime.ReactInstance$ReactJsExceptionHandlerImpl.reportJsException(ReactInstance.java:336)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.jni.NativeRunnable.run(Native Method)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at android.os.Handler.handleCallback(Handler.java:751)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at android.os.Handler.dispatchMessage(Handler.java:95)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.bridge.queue.MessageQueueThreadHandler.dispatchMessage(MessageQueueThreadHandler.java:27)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at android.os.Looper.loop(Looper.java:154)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.bridge.queue.MessageQueueThreadImpl.lambda$startNewBackgroundThread$2(MessageQueueThreadImpl.java:217)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  at com.facebook.react.bridge.queue.MessageQueueThreadImpl$$ExternalSyntheticLambda1.run(D8$$SyntheticClass:0)
11-17 17:03:14.828  4376  4399 E unknown:ReactHost:  ... 1 more
11-17 17:03:14.828  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Starting React Native destruction
11-17 17:03:14.829  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): DevSupportManager cleanup
11-17 17:03:14.829  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Move ReactHost to onHostDestroy()
11-17 17:03:14.829  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Stopping all React Native surfaces
11-17 17:03:14.829  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Destroying MemoryPressureRouter
11-17 17:03:14.829  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Resetting ReactContext ref
11-17 17:03:14.829  4376  4376 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Destroying ReactContext
11-17 17:03:14.829  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Resetting ReactInstance ptr
11-17 17:03:14.829  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Destroying ReactInstance
11-17 17:03:14.829  4376  4399 E unknown:ReactNative: Tried to remove non-existent frame callback
11-17 17:03:14.829  4376  4399 W ReactNativeJNI: Scheduler::~Scheduler() was called (address: 0x7e8a7a7820).
11-17 17:03:14.829  4376  4399 W ReactNativeJNI: UIManagerBinding::~UIManagerBinding() was called (address: 0x7e9212a5b8).
11-17 17:03:14.829  4376  4399 W ReactNativeJNI: UIManager::~UIManager() was called (address: 0x7e8a668720).
11-17 17:03:14.832  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Resetting createReactInstance task ref
11-17 17:03:14.832  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Resetting start task ref
11-17 17:03:14.832  4376  4399 W unknown:BridgelessReact: ReactHost{0}.getOrCreateDestroyTask(): Resetting destroy task ref
```

</details>

You will not see anything in the metro javascript console log.

If you rebuild the app but with `newArchEnabled=true` in `android/gradle.properties`, it will work and you will see the headless JS console output in metro indicating it worked:

>  (NOBRIDGE) LOG  ExampleHeadlessTask JS executing. Received taskData: undefined
