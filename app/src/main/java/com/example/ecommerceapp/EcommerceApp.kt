package com.example.ecommerceapp

import android.app.Application
import com.example.ecommerceapp.di.firebaseModule
import com.example.ecommerceapp.di.networkModule
import com.example.ecommerceapp.di.repositoryModule
import com.example.ecommerceapp.di.roomModule
import com.example.ecommerceapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EcommerceApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EcommerceApp)
            modules(
                firebaseModule,
                networkModule,
                viewModelModule,
                repositoryModule,
                roomModule,

            )
        }
    }

}