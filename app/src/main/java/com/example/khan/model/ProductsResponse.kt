package com.example.khan.model

data class ProductsResponse(
    val page: Int,
    val size: Int,
    val total: Int,
    val debug: Any,
    val previous_page: Any,
    val next_page: Any,
    val items: List<Item>,





)