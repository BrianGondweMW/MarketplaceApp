package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PaymentMethodScreen(
    navController: NavController
) {
    val DarkBlue = androidx.compose.ui.graphics.Color(0xFF123B6D)
    val LightBlue = androidx.compose.ui.graphics.Color(0xFF1E5A94)
    val Orange = androidx.compose.ui.graphics.Color(0xFFF28C28)
    val GradientBackground = androidx.compose.ui.graphics.Brush.verticalGradient(
        listOf(
            DarkBlue,
            LightBlue
        )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Choose Payment Method",
            style = MaterialTheme.typography.headlineMedium,
            color = androidx.compose.ui.graphics.Color.White
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("pay_changu") },
            modifier = Modifier.width(230.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Orange
            )
        ) {
            Text(
                text ="Pay with Pay Changu",
            color = Color.White)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.width(230.dp)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )
        ) {
            Text(
                text = "Back",
            color = Color.Black)
        }
    }
}
