package com.app.wallmount.controller.apis



import android.annotation.SuppressLint
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HttpsURLConnection

object RetrofitHelper {

    private const val BASE_URL="http://airtouch.theappkit.com/public/api/"


    //    fun getInstance(): Retrofit{
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
    @SuppressLint("SuspiciousIndentation")
    fun getInstance(): Retrofit {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        var client: OkHttpClient = OkHttpClient.Builder()
            .hostnameVerifier { _, _ ->
                HttpsURLConnection.getDefaultHostnameVerifier()
                true
            }
            .connectTimeout(30000, TimeUnit.SECONDS)
            .readTimeout(30000, TimeUnit.SECONDS)
            .writeTimeout(30000,TimeUnit.SECONDS).build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


}