package com.example.ecommerceapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products_tables")
data class ProductsDbo (
    @PrimaryKey(autoGenerate = true)
    val id: Int =0,
    val image: String,
    val title: String
)