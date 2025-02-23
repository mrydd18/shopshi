package com.example.ecommerceapp.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductDto(
    @PrimaryKey val id: Int?,  // id should be unique for each product
    val title: String?,
    val price: Double?,
    val description: String?,
    val category: String?,
    val image: String?,
    val rating: RatingDto?
)

data class RatingDto(
    val rate: Double?,
    val count: Int?
)




