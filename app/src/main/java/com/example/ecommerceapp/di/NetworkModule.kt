package com.example.ecommerceapp.di

import com.example.ecommerceapp.core.Constants.BASE_URL
import com.example.ecommerceapp.data.service.ProductService
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(ProductService::class.java)
    }

}