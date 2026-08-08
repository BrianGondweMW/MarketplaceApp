package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    product: Product,
    navController: NavController,
    productViewModel: ProductViewModel = viewModel()
) {

    var userRating by remember {
        mutableStateOf(0)
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val cartDataStore = CartDataStore(context)


    val formatter = NumberFormat.getNumberInstance(Locale.US).apply {
        minimumFractionDigits = 2
        maximumFractionDigits = 2
    }


    // Theme Colors
    val DarkBlue = Color(0xFF123B6D)
    val LightBlue = Color(0xFF1E5A94)
    val Orange = Color(0xFFF28C28)

    val GradientBackground = Brush.verticalGradient(
        listOf(
            DarkBlue,
            LightBlue
        )
    )


    LaunchedEffect(product.id) {

        productViewModel.getUserRating(product.id) { savedRating ->

            userRating = savedRating ?: 0

        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
    ) {


        Column(

            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(16.dp)

        ) {


            TopAppBar(

                title = {

                    Text(
                        text = "Product Details",
                        color = Color.White
                    )

                },


                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },


                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )



            Spacer(
                modifier = Modifier.height(16.dp)
            )



            AsyncImage(
                model = if (product.imageUrl.isNotEmpty())
                    product.imageUrl
                else
                    R.drawable.ic_launcher_background,

                contentDescription = product.name,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(370.dp)
                    .clip(RoundedCornerShape(12.dp)),

                contentScale = ContentScale.Crop
            )




            Spacer(
                modifier = Modifier.height(16.dp)
            )



            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(12.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )

            ) {


                Column(
                    modifier = Modifier.padding(16.dp)
                ) {


                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = product.category,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )


                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )


                    Text(

                        text = "MWK ${formatter.format(product.priceAsDouble)}",

                        style = MaterialTheme.typography.displaySmall,

                        fontWeight = FontWeight.Bold,

                        color = Orange

                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )



                    Text(
                        text = "Rate this product",
                        fontWeight = FontWeight.Bold
                    )


                    Row {

                        repeat(5) { index ->


                            IconButton(

                                onClick = {

                                    userRating = index + 1

                                    productViewModel.saveUserRating(
                                        product.id,
                                        userRating
                                    )

                                },

                                modifier = Modifier.size(40.dp)

                            ) {


                                Icon(

                                    imageVector = if (index < userRating)
                                        Icons.Default.Star
                                    else
                                        Icons.Default.StarBorder,


                                    contentDescription = "Star",


                                    tint = Color(0xFFFFD700),


                                    modifier = Modifier.size(28.dp)

                                )
                            }
                        }
                    }


                    Text(
                        text = "Your Rating: $userRating/5",
                        color = Color.Gray
                    )


                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )


                    Text(
                        text = "Description",
                        fontWeight = FontWeight.Bold
                    )


                    Text(
                        text = product.description
                    )

                }
            }



            Spacer(
                modifier = Modifier.height(20.dp)
            )



            // Smaller Add To Cart Button

            Button(

                onClick = {

                    CartManager.addToCart(product)

                    scope.launch {

                        cartDataStore.saveCart(
                            CartManager.cartItems
                        )

                    }

                },


                modifier = Modifier
                    .width(200.dp)
                    .height(45.dp)
                    .align(Alignment.CenterHorizontally),


                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange
                )

            ) {

                Text(
                    text = "Add to Cart",
                    color = Color.White
                )

            }



            Spacer(
                modifier = Modifier.height(10.dp)
            )



            // Smaller Buy Now Button

            Button(

                onClick = {

                    CartManager.addToCart(product)

                    scope.launch {

                        cartDataStore.saveCart(
                            CartManager.cartItems
                        )

                    }


                    navController.navigate(
                        "checkout"
                    )

                },


                modifier = Modifier
                    .width(200.dp)
                    .height(45.dp)
                    .align(Alignment.CenterHorizontally),


                colors = ButtonDefaults.buttonColors(
                    containerColor = Orange
                )

            ) {

                Text(
                    text = "Buy Now",
                    color = Color.White
                )

            }

        }
    }
}