package com.github.uryyyyyyy.pomer

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import com.facebook.react.ReactActivity
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : ReactActivity() {

    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun getMainComponentName(): String? {
        return "pomer"
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "id")
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "name")
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        Log.i("myActivitysssss", this.mFirebaseAnalytics?.toString())
        this.mFirebaseAnalytics?.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle)
    }
}