package com.example.khan.network

import com.example.khan.model.Item
import com.example.khan.model.ProductsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TimbuApiService {

    // Returns all the products in the database
    @GET("products")
    suspend fun getProducts(
        @Query("Apikey") apiKey: String,
        @Query("organization_id") organizationId: String,
        @Query("Appid") appId: String,
        ): Response<ProductsResponse>

    // Returns the specified product
    @GET("products/{product_Id}")
    suspend fun getProduct(
        @Path("product_Id") productId: String,
        @Query("Apikey") apiKey:String,
        @Query("organization_id") organizationId: String,
        @Query("Appid") appId: String
    ): Response<Item>

}