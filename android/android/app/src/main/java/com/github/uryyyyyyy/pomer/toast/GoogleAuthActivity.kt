package com.github.uryyyyyyy.pomer.toast

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout

import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient

import android.provider.AlarmClock.EXTRA_MESSAGE
import com.github.uryyyyyyy.pomer.R
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.FirebaseAuth


class GoogleAuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = intent
        val message = intent.getStringExtra(EXTRA_MESSAGE)

        val layout = LinearLayout(this)

        val loginButton = Button(this)
        loginButton.text = message
        loginButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        loginButton.setOnClickListener { signIn() }
        layout.addView(loginButton)

        val closeButton = Button(this)
        closeButton.text = "close"
        closeButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT)
        closeButton.setOnClickListener { closeWithParam() }
        layout.addView(closeButton)

        setContentView(layout)
    }

    private fun closeWithParam() {
        finish()
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        val mGoogleApiClient = GoogleApiClient.Builder(this)
                .enableAutoManage(this) { Log.d(TAG, "ConnectionCallbacks:onConnectionFailed") }
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d(TAG, "onActivityResult")

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            Log.d(TAG, "status: " + result.status)
            Log.d(TAG, "result.isSuccess()" + result.isSuccess)
            if (result.isSuccess()) {
                Log.d(TAG, "result.isSuccess()" + result.isSuccess())
                // Google Sign In was successful, authenticate with Firebase
                val account = result.signInAccount!!
                Log.d(TAG, "accountId: " + account.id)
                Log.d(TAG, "idToken: " + account.idToken)
                firebaseAuthWithGoogle(account)
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.id!!)

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)

        val mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful())
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "signInWithCredential", task.getException())
                    }
                    // ...
                }
    }

    companion object {
        private val TAG = "DisplayMessageActivity"
        private val RC_SIGN_IN = 1
    }
}
