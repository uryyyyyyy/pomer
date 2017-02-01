package com.github.uryyyyyyy.pomer.toast

import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.UiThreadUtil
import com.facebook.react.common.MapBuilder

class MyToastModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    override fun getName(): String {
        return "MyToastAndroid"
    }

    override fun getConstants(): Map<String, Any>? {
        val constants = MapBuilder.newHashMap<String, Any>()
        constants.put("SHORT", Toast.LENGTH_SHORT)
        constants.put("LONG", Toast.LENGTH_LONG)
        return constants
    }

    @ReactMethod
    fun show(message: String, duration: Int) {
        UiThreadUtil.runOnUiThread { Toast.makeText(reactApplicationContext, message, duration).show() }
    }
}