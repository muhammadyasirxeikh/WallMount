package com.app.wallmount.controller.apis

import com.app.wallmount.controller.Weatherdataclass.WeatherDataClass
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query


private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
class Constants {


    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface ApiCall {
    @POST("forecast")
    fun weatherDetails(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String
    ): Call<WeatherDataClass>
}