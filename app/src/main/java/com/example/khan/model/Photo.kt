package com.example.khan.model

data class Photo(
    val file_rename: Boolean,
    val filename: String,
    val is_featured: Boolean,
    val is_public: Boolean,
    val model_id: String,
    val model_name: String,
    val organization_id: String,
    val position: Int,
    val save_as_jpg: Boolean,
    val url: String
)