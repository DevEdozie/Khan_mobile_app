package com.example.khan.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// RetrofitClient object to create and configure Retrofit instance
object RetrofitClient {

    // Base URL for the API calls
    const val BASE_URL = "https://api.timbu.cloud/"

    // Configure HttpLoggingInterceptor for logging network requests and responses
    private val logging = HttpLoggingInterceptor().apply {
        // Log entire request and response body
        level = HttpLoggingInterceptor.Level.BODY
    }

    // Create OkHttpClient with logging interceptor
    private val client = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()


    // Retrofit instance created lazily using a lazy initialization pattern
    // Ensures retrofit is created only when needed and avoids unnecessary object creation
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())  // Add Gson converter for JSON parsing
            .client(client)
            .build()
    }
}


// ApiClient object to provide access to the TimbuApiService interface
object ApiClient {
    val apiService: TimbuApiService by lazy {
        // Create TimbuApiService instance using Retrofit instance from RetrofitClient
        RetrofitClient.retrofit.create(TimbuApiService::class.java)
    }

}