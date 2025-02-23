package com.example.ecommerceapp.data.local

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ecommerceapp.data.local.entity.ProductsDao
import com.example.ecommerceapp.data.local.entity.ProductsDbo


@Database(
    entities = [ProductsDbo::class],
    version = 1,
    )
abstract class ProductsDatabases : RoomDatabase() {
    abstract val productsDao: ProductsDao

    companion object {
        fun create(context: Context): ProductsDatabases {
            return Room.databaseBuilder(
                context = context,
                ProductsDatabases::class.java,
                "ProductsDatabases"
            ).build()
        }
    }
}