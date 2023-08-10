package com.app.wallmount.repository

import android.content.Context
import com.app.wallmount.controller.apis.ApiService
import com.app.wallmount.controller.apis.RetrofitHelper
import com.app.wallmount.controller.fragment.forOurGuestDetails.response.SingleGuestRequest
import com.app.wallmount.controller.fragment.password.response.SignInRequest
import com.app.wallmount.controller.fragment.welcome.PropertyRequest

class AppRepository(context:Context) {

    private var mApi=RetrofitHelper
    private val apiCall=mApi.getInstance().create(ApiService::class.java)




    suspend fun signIn(request: SignInRequest) =apiCall.signIn(request)
    suspend fun getlogout(token:String,request: SignInRequest) =apiCall.getlogout(token,request)
    suspend fun getPropertiesList(token:String) =apiCall.getPropertyList(token)
    suspend fun getProfile(token:String) =apiCall.getProfile(token)
    suspend fun getWelcomeScreen(token:String,request: PropertyRequest) =apiCall.getwelcome_screen(token,request)
    suspend fun getHomeScreen(token:String,request: PropertyRequest) =apiCall.gethomeScreen(token,request)
    suspend fun getOurGuests(token:String,request: PropertyRequest) =apiCall.getOurGuest(token,request)
    suspend fun getPropertyDetail(token:String,request: PropertyRequest) =apiCall.getPropertyDetail(token,request)
    suspend fun getServices(token:String,request: PropertyRequest) =apiCall.getServices(token,request)
    suspend fun getFaqs(token:String,request: PropertyRequest) =apiCall.getFaqs(token,request)
    suspend fun getReviews(token:String,request: PropertyRequest) =apiCall.getReviews(token,request)
    suspend fun getGuestDetails(token:String,request: SingleGuestRequest) =apiCall.getGuestDetail(token,request)



}