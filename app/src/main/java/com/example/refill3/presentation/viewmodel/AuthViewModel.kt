package com.example.refill3.presentation.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class AuthViewModel : ViewModel() {
    private val auth = FirebaseAuth.getInstance()
    private var verificationId: String? = null

    fun startPhoneNumberVerification(phoneNumber: String, activity: Activity, callbacks:
    PhoneAuthProvider.OnVerificationStateChangedCallbacks) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            phoneNumber,
            60,
            TimeUnit.SECONDS,
            activity,
            callbacks
        )
    }

    fun verifyCode(code: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        val credential = PhoneAuthProvider.getCredential(verificationId ?: return, code)
        signInWithPhoneAuthCredential(credential, onSuccess, onFailure)
    }

    internal fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onSuccess()
                } else {
                    onFailure(task.exception ?: Exception("Authentication failed"))
                }
            }
    }

    fun storeVerificationId(id: String) {
        verificationId = id
    }
}