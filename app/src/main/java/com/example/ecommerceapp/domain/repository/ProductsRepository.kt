package com.example.ecommerceapp.domain.repository

import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.domain.model.Product

interface ProductsRepository {
    suspend fun getProducts(): OperationStatus<List<Product>>
    suspend fun addToCart(product: Product): OperationStatus<Unit>
    suspend fun deleteFromCart(product: Product): OperationStatus<Unit>
    suspend fun getAllAddedProducts(): OperationStatus<List<Product>>
    suspend fun getItemById(productId: Int): OperationStatus<Product>
    suspend fun deleteAllSavedItems() : OperationStatus<Unit>
}