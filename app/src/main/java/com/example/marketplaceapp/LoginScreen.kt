package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Colors
    val DarkBlue = Color(0xFF123B6D)
    val LightBlue = Color(0xFF1E5A94)
    val GradientBackground = Brush.verticalGradient(
        listOf(DarkBlue, LightBlue)
    )
    val Orange = Color(0xFFF28C28)

    // Reusable colors for fields
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,

        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,

        focusedLabelColor = Orange,
        unfocusedLabelColor = Color.DarkGray,

        focusedBorderColor = Orange,
        unfocusedBorderColor = Color.Gray,

        cursorColor = Orange,

        focusedLeadingIconColor = Orange,
        unfocusedLeadingIconColor = Color.Gray
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Marketplace",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Welcome back! Login to continue shopping",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black
                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                leadingIcon = {
                    Icon(
                        Icons.Default.Email,
                        contentDescription = null
                    )
                },
                colors = fieldColors
            )

            Spacer(modifier = Modifier.height(16.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black
                ),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation =
                    if (passwordVisible)
                        VisualTransformation.None
                    else
                        PasswordVisualTransformation(),

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),

                leadingIcon = {
                    Icon(
                        Icons.Default.Lock,
                        contentDescription = null
                    )
                },

                trailingIcon = {
                    val image =
                        if (passwordVisible)
                            Icons.Filled.Visibility
                        else
                            Icons.Filled.VisibilityOff

                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },

                colors = fieldColors
            )

            Spacer(modifier = Modifier.height(24.dp))

            // LOGIN BUTTON
            Button(
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        message = "Please enter email and password"
                        return@Button
                    }

                    isLoading = true

                    FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(
                            email,
                            password
                        )
                        .addOnCompleteListener { task ->

                            isLoading = false

                            if (task.isSuccessful) {

                                message = "Login Successful"

                                navController.navigate("home") {
                                    popUpTo("login") {
                                        inclusive = true
                                    }
                                }

                            } else {

                                message =
                                    "Login Failed: ${task.exception?.message}"
                            }
                        }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),

                shape = RoundedCornerShape(12.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange
                ),

                enabled = !isLoading
            ) {

                Text(
                    text =
                        if (isLoading)
                            "Logging in..."
                        else
                            "Login",

                    color = Color.White,

                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = message,
                color = Color.White,
                style = MaterialTheme.typography.bodySmall
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {

                Text(
                    "Don't have an account? ",
                    color = Color.White
                )

                Text(
                    text = "Register",
                    color = Orange,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate("register")
                    }
                )
            }
        }
    }
}