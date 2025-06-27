package com.example.refill3.presentation.screens

import android.app.Activity
import android.text.Layout
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.refill3.presentation.viewmodel.AuthViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun AuthenticationScreen(navController: NavHostController, viewModel: AuthViewModel = viewModel()) {
    var phoneNumber by remember { mutableStateOf("") }
    var code by remember { mutableStateOf("") }
    var isCodeSent by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (!isCodeSent) {
            TextField(value = phoneNumber, onValueChange = { phoneNumber = it }, label = { Text("Phone Number (+254)") })
            Button(onClick = {
                viewModel.startPhoneNumberVerification(
                    phoneNumber,
                    LocalContext.current as Activity,
                    object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                            viewModel.signInWithPhoneAuthCredential(credential, { navController.navigate("main") }, {})
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            // Show error
                        }

                        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
                            viewModel.storeVerificationId(verificationId)
                            isCodeSent = true
                        }
                    }
                )
            }) {
                Text("Send Code")
            }
        } else {
            TextField(value = code, onValueChange = { code = it }, label = { Text("Verification Code") })
            Button(onClick = {
                viewModel.verifyCode(code, { navController.navigate("main") }, { /* Show error */ })
            }) {
                Text("Verify")
            }
        }
    }
}