package com.example.marketplaceapp

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
class ProductRepository{
    private val db = FirebaseFirestore.getInstance()
    fun getProducts(
        onResult: (List<Product>) -> Unit
    ){
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val products = result.documents.mapNotNull { document ->

                    Product(
                        id = document.id,
                        name = document.getString("name") ?: "",
                        price = document.getString("price") ?: "",
                        imageUrl = document.getString("imageUrl") ?: "",
                        rating = document.getDouble("rating") ?: 0.0,
                        isLiked = document.getBoolean("isLiked") ?: false,
                        description = document.getString("description") ?: "",
                        category = document.getString("category") ?: "",
                        quantity = document.getLong("quantity")?.toInt() ?: 0
                    )
                }
                onResult(products)
            }
    }

    fun updateRating(product: Product,
                     onComplete: () -> Unit) {
        db.collection("products")
            .document(product.id)
            .update("rating", product.rating)
            .addOnSuccessListener { onComplete() }
    }
    fun saveUserRating(
        productId: String,
        rating: Int
    ){
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        db.collection("user_ratings")
            .document(userId)
            .collection("ratings")
            .document(productId)
            .set(mapOf("rating" to rating))

    }
    fun getUserRating(
        productId: String,
        onResult: (Int?) -> Unit
    ) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        db.collection("user_ratings")
            .document(userId)
            .collection("ratings")
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                val rating = document.getLong("rating")?.toInt() ?: 0
                onResult(rating)
            }
    }
    fun saveUserLike(
        productId: String,
        liked: Boolean
    ){
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        db.collection("user_likes")
            .document(userId)
            .collection("likes")
            .document(productId)
            .set(mapOf("liked" to liked))
    }
    fun getUserLike(
        productId: String,
        onResult: (Boolean?) -> Unit
    ){
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        db.collection("user_likes")
            .document(userId)
            .collection("likes")
            .document(productId)
            .get()
            .addOnSuccessListener { document ->
                val liked = document.getBoolean("liked") ?: false
                onResult(liked)
            }
    }

    fun updateLike(product: Product, onComplete: () -> Unit) {
        db.collection("products")
            .document(product.id)
            .update("isLiked", product.isLiked)
            .addOnSuccessListener { onComplete() }
    }
}
