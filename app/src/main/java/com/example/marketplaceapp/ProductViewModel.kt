package com.example.marketplaceapp

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class ProductViewModel : ViewModel() {
    private val repository = ProductRepository()
    var  products = mutableStateOf<List<Product>>(emptyList())
        private set

    init {
        loadProducts()
    }

   fun loadProducts() {
        repository.getProducts { result ->
            products.value = result
        }
    }
    fun updateLike(updatedProduct: Product) {
        repository.updateLike(updatedProduct) {
            loadProducts()
        }
    }
    fun updateRating(updatedProduct: Product) {
        repository.updateRating(updatedProduct) {
            loadProducts()
        }
    }
    fun saveUserRating(productId: String, rating: Int) {
        repository.saveUserRating(productId, rating)
    }
    fun getUserRating(productId: String, onResult: (Int?) -> Unit) {
        repository.getUserRating(productId, onResult)
    }
    fun saveUserLike(productId: String, liked: Boolean) {
        repository.saveUserLike(productId, liked)
    }
    fun getUserLike(productId: String, onResult: (Boolean?) -> Unit) {
        repository.getUserLike(productId, onResult)
    }
}
