package com.github.uryyyyyyy.pomer.toast

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.UiThreadUtil
import com.facebook.react.common.MapBuilder
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.util.Calendar.SHORT

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
        Log.i("myActivitysssss", "toast")
        UiThreadUtil.runOnUiThread { Toast.makeText(reactApplicationContext, message, duration).show() }
    }


    @ReactMethod
    fun showModal() {
        val intent = Intent(reactApplicationContext, GoogleAuthActivity::class.java)
        val message = "loginしてください"
        intent.putExtra(EXTRA_MESSAGE, message)
        ActivityCompat.startActivity(currentActivity, intent, null)
    }

    @ReactMethod
    fun showCurrentUser() {
        val user: FirebaseUser? = FirebaseAuth.getInstance().currentUser
        val message = "login as: " + user!!.email
        Log.i("myActivitysssss", "toast")
        UiThreadUtil.runOnUiThread { Toast.makeText(reactApplicationContext, message, SHORT).show() }
    }

    @ReactMethod
    fun sendEvent() {
        val mFirebaseAnalytics = FirebaseAnalytics.getInstance(reactApplicationContext)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        Log.i("myActivitysssss", mFirebaseAnalytics.toString())
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}