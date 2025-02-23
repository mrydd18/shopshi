package com.example.ecommerceapp.di

import androidx.room.Room
import com.example.ecommerceapp.data.local.ProductsDatabases
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ProductsDatabases::class.java,
            "ProductsDatabases"
        ).build()
    }

    // Provide the MovieDao instance
    single {
        get<ProductsDatabases>().productsDao
    }
}