package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun CheckoutScreen(navController: NavController) {

    val DarkBlue = Color(0xFF123B6D)
    val LightBlue = Color(0xFF1E5A94)
    val Orange = Color(0xFFF28C28)

    val GradientBackground = Brush.verticalGradient(
        listOf(
            DarkBlue,
            LightBlue
        )
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
            .padding(start = 16.dp, end = 16.dp, top = 50.dp, bottom = 16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {


        Text(
            text = "Checkout",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )


        Text(
            text = "Total Amount",
            color = Color.White
        )


        Text(
            text = "MK${CartManager.getTotal()}",
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall
        )


        Spacer(
            modifier = Modifier.height(20.dp)
        )



        Button(

            onClick = {
                navController.navigate("payment_method")
            },

            modifier = Modifier.width(230.dp)
                .height(50.dp),



            colors = ButtonDefaults.buttonColors(
                containerColor = Orange
            )

        ) {

            Text(
                text = "Continue to Payment",
                color = Color.White
            )

        }

Spacer(
    modifier = Modifier.height(10.dp)
)

        Button(

            onClick = {
                navController.popBackStack()
            },

            modifier = Modifier.width(230.dp)
                .height(50.dp),



            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            )

        ) {

            Text(
                text = "Cancel",
                color = DarkBlue
            )

        }

    }
}