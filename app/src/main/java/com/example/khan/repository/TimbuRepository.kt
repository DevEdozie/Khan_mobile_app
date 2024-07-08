package com.example.khan.repository

import com.example.khan.model.BaseResponse
import com.example.khan.model.Item
import com.example.khan.model.ProductsResponse
import com.example.khan.network.ApiClient
import retrofit2.Response

// TimbuRepository class to handle data fetching from the Timbu API
class TimbuRepository : BaseRepository() {

    // Method to fetch a list of products
    suspend fun getProducts(): BaseResponse<ProductsResponse> {
        // Use safeApiCall  to handle network calls with error handling
        return safeApiCall {
            // Call getProducts method of TimbuApiService using ApiClient
            ApiClient.apiService.getProducts(
                organizationId = "45568134721a48dd9c21e80515f155ff",
                appId = "BWQKWQAF8SX0A7O",
                apiKey = "00b3b807cef546d494105648b7e3bfc220240705090455672155"
            )
        }
    }

    // Method to fetch a single product by ID
    suspend fun getProduct(productId: String): BaseResponse<Item> {
        // Use safeApiCall to handle network calls with error handling
        return safeApiCall {
            // Call getProduct method of TimbuApiService using ApiClient
            ApiClient.apiService.getProduct(
                organizationId = "45568134721a48dd9c21e80515f155ff",
                appId = "BWQKWQAF8SX0A7O",
                apiKey = "00b3b807cef546d494105648b7e3bfc220240705090455672155",
                productId = productId
            )
        }
    }
}