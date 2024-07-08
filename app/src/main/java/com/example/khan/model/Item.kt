package com.example.khan.model

import kotlinx.parcelize.Parcelize


data class Item(
    val available_quantity: Any,
    val buying_price: Any,
    val categories: List<Any>,
    val current_price: List<CurrentPrice>,
    val date_created: String,
    val description: Any,
    val discounted_price: Any,
    val extra_infos: Any,
    val featured_reviews: Any,
    val id: String,
    val is_available: Boolean,
    val is_deleted: Boolean,
    val is_service: Boolean,
    val last_updated: String,
    val name: String,
    val organization_id: String,
    val parent: Any,
    val parent_product_id: Any,
    val photos: List<Photo>,
    val previous_url_slugs: Any,
    val prices: Any,
    val product_image: List<Any>,
    val selling_price: Any,
    val stock_id: Any,
    val stocks: Any,
    val unavailability: List<Any>,
    val unavailable: Boolean,
    val unavailable_end: Any,
    val unavailable_start: Any,
    val unique_id: String,
    val url_slug: String,
    val user_id: String
)