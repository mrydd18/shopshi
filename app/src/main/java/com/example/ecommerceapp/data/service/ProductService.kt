package com.example.ecommerceapp.data.service

import com.example.ecommerceapp.core.Constants
import com.example.ecommerceapp.data.remote.dto.ProductDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {

    @GET("products")
    suspend fun getProducts(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<List<ProductDto>>

    @GET("products/{product_id}")
    suspend fun getItemById(
        @Path("product_id") productId: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<ProductDto>



}