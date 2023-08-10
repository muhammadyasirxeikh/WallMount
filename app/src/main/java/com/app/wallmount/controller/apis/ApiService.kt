package com.app.wallmount.controller.apis

import com.app.wallmount.controller.fragment.Services.response.ServiceResponse
import com.app.wallmount.controller.fragment.faq.response.FaqResponse
import com.app.wallmount.controller.fragment.feedback.response.ReviewResponse
import com.app.wallmount.controller.fragment.forOurGuest.response.ForOurGuestResponse
import com.app.wallmount.controller.fragment.forOurGuestDetails.response.GuestDetailResponse
import com.app.wallmount.controller.fragment.forOurGuestDetails.response.SingleGuestRequest
import com.app.wallmount.controller.fragment.home.response.HomeScreenResponse
import com.app.wallmount.controller.fragment.logout.LogoutResponse
import com.app.wallmount.controller.fragment.password.response.PasswordResponse
import com.app.wallmount.controller.fragment.password.response.SignInRequest
import com.app.wallmount.controller.fragment.profile.ProfileResponse
import com.app.wallmount.controller.fragment.property.response.PropertyDetailResponse
import com.app.wallmount.controller.fragment.propertySelect.response.SelectPropertyResponse
import com.app.wallmount.controller.fragment.welcome.PropertyRequest
import com.app.wallmount.controller.fragment.welcome.WelcomeScreenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("sign_in")
    suspend fun signIn(
        @Body request: SignInRequest
    ): Response<PasswordResponse>

    @GET("property_list")
    suspend fun getPropertyList(
        @Header("Authorization") token: String
    ): Response<SelectPropertyResponse>

    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>

    @POST("welcome_screen")
    suspend fun getwelcome_screen(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<WelcomeScreenResponse>

    @POST("home")
    suspend fun gethomeScreen(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<HomeScreenResponse>

    @POST("our_guests")
    suspend fun getOurGuest(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<ForOurGuestResponse>

    @POST("single_property")
    suspend fun getPropertyDetail(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<PropertyDetailResponse>

    @POST("single_guest")
    suspend fun getGuestDetail(
        @Header("Authorization") token: String,
        @Body request: SingleGuestRequest
    ): Response<GuestDetailResponse>

    @POST("services")
    suspend fun getServices(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<ServiceResponse>

    @POST("faqs")
    suspend fun getFaqs(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<FaqResponse>

    @POST("logout")
    suspend fun getlogout(
        @Header("Authorization") token: String,
        @Body request: SignInRequest
    ): Response<LogoutResponse>

    @POST("get_reviews")
    suspend fun getReviews(
        @Header("Authorization") token: String,
        @Body request: PropertyRequest
    ): Response<ReviewResponse>

}