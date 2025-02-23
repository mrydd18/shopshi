package com.example.ecommerceapp.di


import com.example.ecommerceapp.domain.repository.ProductsRepository
import com.example.ecommerceapp.data.repository.ProductsRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::ProductsRepositoryImpl) bind ProductsRepository::class

}
