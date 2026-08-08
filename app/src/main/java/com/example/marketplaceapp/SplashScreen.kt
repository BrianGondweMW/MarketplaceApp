package com.example.marketplaceapp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
@Composable
fun SplashScreen(
    navController: NavController
) {
    LaunchedEffect(Unit) {
        delay(1800)
        navController.navigate("login") {
            popUpTo("splash") {
                inclusive = true
            }
        }
    }
    val darkBlue = Color(0xFF123B6D)
    val lightBlue = Color(0xFF1E5A94)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        darkBlue,
                        lightBlue
                    )
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(
                id = R.drawable.collection
            ),
            contentDescription = "Marketplace App Logo",
            modifier = Modifier.size(130.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(
            modifier = Modifier.height(24.dp)
        )
        Text(
            text = "Marketplace App",
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        Text(
            text = "Shop • Discover • Enjoy",
            color = Color.White.copy(alpha = 0.85f),
            fontSize = 15.sp
        )
    }
}