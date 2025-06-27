package com.example.refill3.presentation.screens.auth

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.refill3.data.repositories.AuthRepository
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen(navController: NavHostController) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Text("Sign Up", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") }
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirm Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            // error is populated on the condition that an error is encountered
            // the error variable will be populated with a text composable
            // showing the error
            error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(  onClick  = {
                if (password != confirmPassword) {
                    error = "password does not match!!"
                } else {
                    // register our user to firebase
                    registerUser(email, password,
                        context ,navController,
                        onError = {errorMsg  -> error = errorMsg})
                }
            }
            ) {
                Text("Sign Up")
            }

            Spacer(modifier = Modifier.height(8.dp))
//navigate to login //navigate to login
            TextButton(onClick = {  navController.navigate("login") })
            {
                Text("Already have an account? Login")
            }

        }

    }
}
fun registerUser(email: String, password: String,
                 context: Context, navController: NavController,
                 onError: (String)-> Unit ) {
    Firebase.auth.createUserWithEmailAndPassword(email,password)
        .addOnCompleteListener{ task ->
            if(task.isSuccessful){
                navController.navigate("login")
            } else {
                onError(task.exception?.message ?:
                "Registration failed")
            }
        }
}


@Preview
@Composable
fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen(rememberNavController())
    }
}

