package com.example.application.data.model

data class Feed( val title: String, val description: String,  val content: String,
                val author: String, val published_at: String, val highlight: String,
                 val url: String, val image_url: String, val favorite: Boolean = false)