package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisterScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    // Colors
    // Colors
    val DarkBlue = Color(0xFF123B6D)
    val LightBlue = Color(0xFF1E5A94)
    val GradientBackground = Brush.verticalGradient(
        listOf(DarkBlue, LightBlue)
    )
    val Orange = Color(0xFFF28C28)

    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        focusedTextColor = Color.Black,
        unfocusedTextColor = Color.Black,
        focusedLabelColor = DarkBlue,
        unfocusedLabelColor = Color.Gray,
        focusedBorderColor = DarkBlue,
        unfocusedBorderColor = Color.LightGray,
        cursorColor = DarkBlue
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
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Create Account",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sign up to start shopping",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(24.dp))

            // FULL NAME
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Full Name") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.Gray) },
                colors = fieldColors
            )
            Spacer(modifier = Modifier.height(12.dp))

            // EMAIL
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Email") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                leadingIcon = { Icon(Icons.Default.Email, contentDescription = null, tint = Color.Gray) },
                colors = fieldColors
            )
            Spacer(modifier = Modifier.height(12.dp))

            // PASSWORD WITH EYE
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Password") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray) },
                trailingIcon = {
                    val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle password", tint = Color.Gray)
                    }
                },
                colors = fieldColors
            )
            Spacer(modifier = Modifier.height(12.dp))

            // CONFIRM PASSWORD WITH EYE
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Confirm Password") },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Black),
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.Gray) },
                trailingIcon = {
                    val image = if (confirmPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { confirmPasswordVisible = !confirmPasswordVisible }) {
                        Icon(imageVector = image, contentDescription = "Toggle confirm password", tint = Color.Gray)
                    }
                },
                colors = fieldColors
            )

            if (message.isNotEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(text = message, color = Color.White, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.isBlank() || email.isBlank() || password.isBlank()) {
                        message = "Please fill all fields"
                        return@Button
                    }
                    if (password != confirmPassword) {
                        message = "Passwords do not match"
                        return@Button
                    }
                    isLoading = true
                    val auth = FirebaseAuth.getInstance()
                    val db = FirebaseFirestore.getInstance()

                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val userId = auth.currentUser?.uid ?: ""
                                val userMap = hashMapOf("name" to name, "email" to email)
                                db.collection("users").document(userId).set(userMap)
                                    .addOnSuccessListener {
                                        isLoading = false
                                        navController.navigate("home") {
                                            popUpTo("register") { inclusive = true }
                                        }
                                    }
                                    .addOnFailureListener {
                                        isLoading = false
                                        message = "Error saving user data"
                                    }
                            } else {
                                isLoading = false
                                message = "Registration Failed: ${task.exception?.message}"
                            }
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Orange),
                enabled = !isLoading
            ) {
                Text(if (isLoading) "Creating..." else "Register", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Text("Already have an account? ", color = Color.White)
                Text(
                    text = "Login",
                    color = Orange,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable { navController.navigate("login") }
                )
            }
        }
    }
}