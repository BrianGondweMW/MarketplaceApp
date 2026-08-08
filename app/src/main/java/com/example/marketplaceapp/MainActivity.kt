package com.example.marketplaceapp
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.marketplaceapp.ui.theme.MarketplaceAppTheme
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
class MainActivity : ComponentActivity() {
    private var paymentSuccessful = false
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        paymentSuccessful =
            intent?.data?.scheme == "marketplaceapp" &&
                    intent?.data?.host == "payment-success"
        enableEdgeToEdge()
        val cartDataStore = CartDataStore(this)
        lifecycleScope.launch {
            cartDataStore.cart.collect { savedCart ->
                CartManager.cartItems.clear()
                CartManager.cartItems.addAll(savedCart)
            }
        }
        setContent {
            MarketplaceAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = if (paymentSuccessful) {
                        "payment_success"
                    } else {
                        "splash"
                    }
                ) {
                    // -------------------------
                    // SPLASH
                    // -------------------------
                    composable("splash") {
                        SplashScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // LOGIN
                    // -------------------------
                    composable("login") {
                        LoginScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // REGISTER
                    // -------------------------
                    composable("register") {
                        RegisterScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // HOME
                    // -------------------------
                    composable("home") {
                        HomeScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // PRODUCT DETAILS
                    // -------------------------
                    composable("view details") {
                        val product =
                            navController
                                .previousBackStackEntry
                                ?.savedStateHandle
                                ?.get<Product>("product")
                        if (product != null) {
                            ProductDetailsScreen(
                                product = product,
                                navController = navController
                            )
                        }
                    }
                    // -------------------------
                    // CART
                    // -------------------------
                    composable("cart") {
                        CartScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // CHECKOUT
                    // -------------------------
                    composable("checkout") {
                        CheckoutScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // PAYMENT METHOD
                    // -------------------------
                    composable("payment_method") {
                        PaymentMethodScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // PAYCHANGU
                    // -------------------------
                    composable("pay_changu") {
                        PayChanguScreen(
                            navController = navController
                        )
                    }
                    // -------------------------
                    // PAYMENT SUCCESS
                    // -------------------------
                    composable("payment_success") {
                        PaymentScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        if (
            intent.data?.scheme == "marketplaceapp" &&
            intent.data?.host == "payment-success"
        ) {
            paymentSuccessful = true
        }
    }
}
@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarketplaceAppTheme {
        Greeting("Android")
    }
}