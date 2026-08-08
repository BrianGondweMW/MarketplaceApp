package com.example.marketplaceapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

@Composable
fun PayChanguScreen(
    navController: NavController
) {
    val context = androidx.compose.ui.platform.LocalContext.current
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val totalAmount = CartManager.getTotal()

    val darkBlue = Color(0xFF123B6D)
    val lightBlue = Color(0xFF1E5A94)
    val orange = Color(0xFFF28C28)

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            darkBlue,
            lightBlue
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .navigationBarsPadding()
            .padding(horizontal = 20.dp, vertical = 24.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 420.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "PayChangu Payment",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = "Total Amount",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "MK $totalAmount",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {

                    isLoading = true
                    errorMessage = ""

                    scope.launch {

                        try {

                            val checkoutUrl = withContext(Dispatchers.IO) {

                                val url = URL(
                                    "http://10.0.2.2:5001/marketplace-app-d6b93/us-central1/createPayChanguPayment"
                                )

                                val connection =
                                    url.openConnection() as HttpURLConnection

                                connection.requestMethod = "POST"

                                connection.setRequestProperty(
                                    "Content-Type",
                                    "application/json"
                                )

                                connection.doOutput = true

                                val json = JSONObject().apply {
                                    put(
                                        "amount",
                                        totalAmount.toString()
                                    )

                                    put(
                                        "email",
                                        "kennethzirenga@gmail.com"
                                    )

                                    put(
                                        "first_name",
                                        "Kelvin"
                                    )

                                    put(
                                        "last_name",
                                        "Banda"
                                    )
                                }

                                connection.outputStream.use { output ->
                                    output.write(
                                        json.toString().toByteArray()
                                    )
                                }

                                val responseCode =
                                    connection.responseCode

                                val responseText =
                                    if (responseCode in 200..299) {
                                        connection.inputStream
                                            .bufferedReader()
                                            .use { it.readText() }
                                    } else {
                                        connection.errorStream
                                            ?.bufferedReader()
                                            ?.use { it.readText() }
                                            ?: "Payment request failed"
                                    }

                                connection.disconnect()

                                if (responseCode !in 200..299) {
                                    throw Exception(responseText)
                                }

                                val responseJson =
                                    JSONObject(responseText)

                                responseJson
                                    .getJSONObject("data")
                                    .getString("checkout_url")
                            }

                            val intent = Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse(checkoutUrl)
                            )

                            context.startActivity(intent)

                            isLoading = false

                        } catch (e: Exception) {

                            isLoading = false

                            errorMessage =
                                e.message ?: "Unable to start payment"
                        }
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                enabled = !isLoading,

                colors = ButtonDefaults.buttonColors(
                    containerColor = orange
                )
            ) {

                if (isLoading) {

                    CircularProgressIndicator(
                        color = Color.White
                    )

                } else {

                    Text(
                        text = "Proceed to Pay",
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),

                enabled = !isLoading,

                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black
                )
            ) {

                Text("Back")
            }

            if (errorMessage.isNotEmpty()) {

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = errorMessage,
                    color = Color.White
                )
            }
        }
    }
}