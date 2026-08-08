package com.example.marketplaceapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PaymentScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Payment Successful!",
            style = MaterialTheme.typography.headlineMedium,
                    color = androidx.compose.ui.graphics.Color.White
        )
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "Thank you for your purchase!",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "You will receive an email confirmation shortly.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = { navController.navigate("home") {
                popUpTo("home") {
                    inclusive = true
            } }}
        ) {
            Text("Continue Shopping")
        }
    }
}
