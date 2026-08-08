package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {
    val products by productViewModel.products
    val userLikes = remember { mutableStateMapOf<String, Boolean>() }
    var userName by remember { mutableStateOf("") }

    // Colors
    val DarkBlue = Color(0xFF123B6D)
    val LightBlue = Color(0xFF1E5A94)
    val Orange = Color(0xFFF28C28)

    val GradientBackground = Brush.verticalGradient(
        listOf(DarkBlue, LightBlue)
    )

    LaunchedEffect(Unit) {
        productViewModel.loadProducts()

        FirebaseAuth.getInstance().currentUser?.uid?.let { uid ->
            FirebaseFirestore.getInstance()
                .collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener { doc ->
                    userName = doc.getString("name") ?: ""
                }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Top App Bar
            TopAppBar(
                title = {
                    Column {

                        Text(
                            text = "Marketplace",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 22.sp
                        )

                        Text(
                            text = if (userName.isBlank())
                                "Hi"
                            else
                                "Hi, $userName",

                            style = MaterialTheme.typography.bodyMedium,

                            color = Color.White.copy(
                                alpha = 0.9f
                            )
                        )
                    }
                },

                actions = {

                    // Cart
                    IconButton(
                        onClick = {
                            navController.navigate("cart")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Cart",
                            tint = Color.White
                        )
                    }

                    // Logout
                    IconButton(
                        onClick = {

                            FirebaseAuth
                                .getInstance()
                                .signOut()

                            navController.navigate("login") {
                                popUpTo("home") {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Logout,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )

            // Browse Products
            Text(
                text = "Browse Products",

                style = MaterialTheme.typography.headlineSmall,

                fontWeight = FontWeight.Bold,

                color = Color.White,

                modifier = Modifier.padding(
                    start = 16.dp,
                    top = 8.dp,
                    bottom = 12.dp
                )
            )

            // Products
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),

                contentPadding = PaddingValues(
                    horizontal = 12.dp,
                    vertical = 8.dp
                ),

                verticalArrangement = Arrangement.spacedBy(
                    16.dp
                )
            ) {

                items(
                    products,
                    key = { it.id }
                ) { product ->

                    LaunchedEffect(product.id) {

                        productViewModel.getUserLike(
                            product.id
                        ) { liked ->

                            userLikes[product.id] =
                                liked == true
                        }
                    }

                    ProductCard(
                        product = product,

                        isLiked =
                            userLikes[product.id] ?: false,

                        onViewDetailsClick = {

                            navController
                                .currentBackStackEntry
                                ?.savedStateHandle
                                ?.set(
                                    "product",
                                    product
                                )

                            navController.navigate(
                                "view details"
                            )
                        },

                        onLikeClick = {

                            val newLike =
                                !(userLikes[product.id] ?: false)

                            userLikes[product.id] =
                                newLike

                            productViewModel.saveUserLike(
                                product.id,
                                newLike
                            )
                        }
                    )
                }
            }
        }
    }
}