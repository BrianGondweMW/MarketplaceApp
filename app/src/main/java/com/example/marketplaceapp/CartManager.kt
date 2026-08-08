package com.example.marketplaceapp

import androidx.compose.runtime.mutableStateListOf

object CartManager {
    val cartItems = mutableStateListOf<Product>()
    
    fun addToCart(product: Product) {
        val existingProduct = cartItems.find {
            it.name == product.name
        }

        if (existingProduct != null) {

            cartItems.remove(existingProduct)

            cartItems.add(
                existingProduct.copy(
                    quantity = existingProduct.quantity + 1
                )
            )

        } else {

            cartItems.add(
                product.copy(quantity = 1)
            )
        }
    }

    fun increaseQuantity(product: Product) {
        val index = cartItems.indexOf(product)
        if (index != -1) {
            cartItems[index] = product.copy(
                quantity = product.quantity + 1
            )
        }
    }

    fun decreaseQuantity(product: Product) {

        if (product.quantity > 1) {

            val index = cartItems.indexOf(product)

            if (index != -1) {

                cartItems[index] = product.copy(

                    quantity = product.quantity - 1

                )

            }

        } else {

            cartItems.remove(product)

        }

    }
    
    fun removeFromCart(product: Product) {
        cartItems.remove(product)
    }
    
    fun getTotal(): Double {
        return cartItems.sumOf {
            product ->
            product.priceAsDouble * product.quantity
        }
    }
}
