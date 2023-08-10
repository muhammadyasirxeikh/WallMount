package com.app.wallmount.di

import com.app.wallmount.controller.apis.ApiService
import com.app.wallmount.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{

        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideDataApi(retrofit: Retrofit):ApiService{
        return retrofit.create(ApiService::class.java)
    }
}