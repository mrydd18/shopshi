package com.example.ecommerceapp.di

import com.example.ecommerceapp.presentation.viewModels.CartViewModel
import com.example.ecommerceapp.presentation.viewModels.DetailsViewModel
import com.example.ecommerceapp.presentation.viewModels.ProductViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ProductViewModel(get()) }
    viewModel { CartViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
}
