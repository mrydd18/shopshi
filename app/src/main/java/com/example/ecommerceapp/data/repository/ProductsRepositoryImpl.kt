package com.example.ecommerceapp.data.repository

import com.example.ecommerceapp.core.ApiCallHelper
import com.example.ecommerceapp.core.Constants
import com.example.ecommerceapp.core.OperationStatus
import com.example.ecommerceapp.core.RoomCallHelper
import com.example.ecommerceapp.core.map
import com.example.ecommerceapp.data.local.entity.ProductsDao
import com.example.ecommerceapp.data.service.ProductService
import com.example.ecommerceapp.data.toProduct
import com.example.ecommerceapp.data.toProductsDbo
import com.example.ecommerceapp.domain.model.Product
import com.example.ecommerceapp.domain.repository.ProductsRepository

class ProductsRepositoryImpl(
    private val service: ProductService,
    private val productsDao : ProductsDao
) : ProductsRepository {
    override suspend fun getProducts(): OperationStatus<List<Product>> {
        return ApiCallHelper.safeApiCall {
            service.getProducts()
        }.map { listOfProductsDto ->
            listOfProductsDto.map {
                it.toProduct()
            }

        }
    }

    override suspend fun addToCart(product: Product): OperationStatus<Unit> {
        return RoomCallHelper.safeRoomCall {
            product.toProductsDbo()?.let { productsDao.saveToCart(product = it) }
        }
    }

    override suspend fun deleteFromCart(product: Product): OperationStatus<Unit> {
        return RoomCallHelper.safeRoomCall {
            product.toProductsDbo()?.let { productsDao.deleteFromCart(it) }
        }
    }

    override suspend fun getAllAddedProducts(): OperationStatus<List<Product>> {
        return RoomCallHelper.safeRoomCall {
            productsDao.getAllSavedCart().map {
                productsDbo ->
                productsDbo.toProduct()
            }
        }

    }

    override suspend fun getItemById(productId: Int): OperationStatus<Product> {
        return ApiCallHelper.safeApiCall {
            service.getItemById(
                productId = productId,
                apiKey = Constants.API_KEY
            )
        }.map { productDto -> productDto.toProduct() }
    }

    override suspend fun deleteAllSavedItems(): OperationStatus<Unit> {
        return RoomCallHelper.safeRoomCall {
            productsDao.deleteAllSavedItems()
            Unit
        }
    }



}