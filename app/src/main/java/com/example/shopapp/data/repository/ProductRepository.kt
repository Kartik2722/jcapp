package com.example.shopapp.data.repository

import com.example.shopapp.data.model.Product
import com.example.shopapp.data.remote.RetrofitClient

class ProductRepository {

    private val api = RetrofitClient.apiService

    suspend fun getAllProducts(): Result<List<Product>> {
        return try {
            val products = api.getAllProducts()
            Result.success(products)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: Int): Result<Product> {
        return try {
            val product = api.getProductById(id)
            Result.success(product)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
