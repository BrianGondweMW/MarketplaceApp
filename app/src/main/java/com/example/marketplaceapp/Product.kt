package com.example.marketplaceapp

import kotlinx.serialization.Serializable
import java.io.Serializable as JavaSerializable

@Serializable
data class Product(
    val id: String = "",
    val name: String = "",
    val price: String = "",
    val imageUrl: String = "",
    var rating: Double = 0.0,
    @get: JvmName("getLiked")
    @set: JvmName("setLiked")
    var isLiked: Boolean = false,
    val description: String = "",
    val category: String = "",
    val quantity: Int = 0
) : JavaSerializable {
    val avgRating: Double get() = rating
}

val Product.priceAsDouble: Double
    get() = price
        .replace("MK", "", ignoreCase = true)
        .replace("MWK", "", ignoreCase = true)
        .replace(",", "")
        .trim()
        .toDoubleOrNull() ?: 0.0
