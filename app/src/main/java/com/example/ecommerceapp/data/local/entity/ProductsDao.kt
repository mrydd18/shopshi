package com.example.ecommerceapp.data.local.entity

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface ProductsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveToCart(product: ProductsDbo)

    @Delete
    suspend fun deleteFromCart(product: ProductsDbo)

    @Query("SELECT * FROM products_tables")
    suspend fun getAllSavedCart(): List<ProductsDbo>

    @Query("DELETE  FROM products_tables")
    suspend fun deleteAllSavedItems(): Unit



}