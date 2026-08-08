package com.example.marketplaceapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage


@Composable
fun CartScreen(navController: NavController) {


    val DarkBlue = Color(0xFF123B6D)
    val LightBlue = Color(0xFF1E5A94)
    val Orange = Color(0xFFF28C28)


    val GradientBackground = Brush.verticalGradient(
        listOf(
            DarkBlue,
            LightBlue
        )
    )


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GradientBackground)
    ) {


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


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



                Text(
                    text = "Shopping Cart",
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.White
                )

            }



            Spacer(
                modifier = Modifier.height(16.dp)
            )



            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {


                items(CartManager.cartItems) { product ->



                    Card(

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),


                        shape = RoundedCornerShape(20.dp),


                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),


                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp
                        )

                    ) {



                        Row(

                            modifier = Modifier.padding(12.dp),

                            verticalAlignment = Alignment.CenterVertically

                        ) {



                            AsyncImage(

                                model = if (product.imageUrl.isNotEmpty())
                                    product.imageUrl
                                else
                                    R.drawable.ic_launcher_background,


                                contentDescription = product.name,


                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(
                                        RoundedCornerShape(16.dp)
                                    )

                            )



                            Spacer(
                                modifier = Modifier.width(16.dp)
                            )



                            Column(
                                modifier = Modifier.weight(1f)
                            ) {



                                Text(
                                    text = product.name,
                                    style = MaterialTheme.typography.titleMedium
                                )



                                Spacer(
                                    modifier = Modifier.height(4.dp)
                                )



                                Text(
                                    text = product.price
                                )



                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )



                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {



                                    Button(
                                        onClick = {
                                            CartManager.decreaseQuantity(product)
                                        },

                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp
                                        )

                                    ) {

                                        Text("-")

                                    }



                                    Text(
                                        text = "${product.quantity}",
                                        modifier = Modifier.padding(horizontal = 12.dp)
                                    )



                                    Button(
                                        onClick = {
                                            CartManager.increaseQuantity(product)
                                        },

                                        contentPadding = PaddingValues(
                                            horizontal = 12.dp
                                        )

                                    ) {

                                        Text("+")

                                    }

                                }



                                Spacer(
                                    modifier = Modifier.height(8.dp)
                                )



                                TextButton(
                                    onClick = {
                                        CartManager.removeFromCart(product)
                                    }
                                ) {

                                    Text(
                                        text = "Remove",
                                        color = Color.Red
                                    )

                                }

                            }

                        }

                    }

                }

            }



            Spacer(
                modifier = Modifier.height(16.dp)
            )



            Text(

                text = "Total: MK${CartManager.getTotal()}",

                style = MaterialTheme.typography.headlineSmall,

                color = Color.White

            )



            Spacer(
                modifier = Modifier.height(12.dp)
            )



            Button(

                onClick = {
                    navController.navigate("checkout")
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

                    text = "Checkout",

                    color = Color.White

                )

            }


        }

    }

}