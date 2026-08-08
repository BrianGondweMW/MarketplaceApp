package com.example.marketplaceapp

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore(
    name = "cart"
)

class CartDataStore(
    private val context: Context
) {

    private val cartKey = stringPreferencesKey("cart_items")


    val cart: Flow<List<Product>> = context.dataStore.data.map { preferences ->

        val json = preferences[cartKey] ?: "[]"

        Json.decodeFromString(json)

    }


    suspend fun saveCart(products: List<Product>) {

        context.dataStore.edit { preferences ->

            preferences[cartKey] = Json.encodeToString(products)

        }
    }
}