package com.reproducerapp

import android.app.Application
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.facebook.react.HeadlessJsTaskService
import com.facebook.react.PackageList
import com.facebook.react.ReactApplication
import com.facebook.react.ReactHost
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.Arguments
import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.load
import com.facebook.react.defaults.DefaultReactHost.getDefaultReactHost
import com.facebook.react.defaults.DefaultReactNativeHost
import com.facebook.react.jstasks.HeadlessJsTaskConfig
import com.facebook.react.soloader.OpenSourceMergedSoMapping
import com.facebook.soloader.SoLoader

class MainApplication : Application(), ReactApplication {

  override val reactNativeHost: ReactNativeHost =
      object : DefaultReactNativeHost(this) {
        override fun getPackages(): List<ReactPackage> =
            PackageList(this).packages.apply {
              // Packages that cannot be autolinked yet can be added manually here, for example:
              // add(MyReactNativePackage())
            }

        override fun getJSMainModuleName(): String = "index"

        override fun getUseDeveloperSupport(): Boolean = BuildConfig.DEBUG

        override val isNewArchEnabled: Boolean = BuildConfig.IS_NEW_ARCHITECTURE_ENABLED
        override val isHermesEnabled: Boolean = BuildConfig.IS_HERMES_ENABLED
      }

  override val reactHost: ReactHost
    get() = getDefaultReactHost(applicationContext, reactNativeHost)

  override fun onCreate() {
    super.onCreate()
    SoLoader.init(this, OpenSourceMergedSoMapping)
    if (BuildConfig.IS_NEW_ARCHITECTURE_ENABLED) {
      // If you opted-in for the New Architecture, we load the native entry point for this app.
      load()
    }
  }
}

class ExampleReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("REPRODUCER", "we received an intent. Starting ExampleService...");
        try {
            var backgroundIntent = Intent()
            backgroundIntent.setClass(context, ExampleService::class.java)
            var name = context.startService(backgroundIntent)
            if (name != null) {
                Log.d("REPRODUCER", "Successfully started ExampleService.")
            } else {
                Log.d("REPRODUCER", "ExampleService not found. Unsuccessful start.")
            }
        } catch (ex: Exception) {
            Log.e("REPRODUCER", "Unable to start ExampleService?", ex)
        }
    }
}

class ExampleService : HeadlessJsTaskService() {
    override fun getTaskConfig(intent: Intent): HeadlessJsTaskConfig? {
        Log.d("REPRODUCER", "ExampleService::getTaskConfig")
        var fakeExtras = Bundle()
        fakeExtras.putString("foo", "bar")
        return HeadlessJsTaskConfig(
            "ExampleHeadlessTask",
            Arguments.fromBundle(fakeExtras),
            60000, // timeout for the task - react-native-firebase uses 60000
            true // react-native-firebase uses true, using true here. Maybe test false
        )
    }
}