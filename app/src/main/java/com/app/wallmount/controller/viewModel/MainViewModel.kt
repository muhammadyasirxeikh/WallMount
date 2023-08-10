package com.app.wallmount.controller.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.wallmount.controller.apis.ApiError
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
import com.app.wallmount.repository.AppRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainViewModel (private val repository: AppRepository): ViewModel(){



    private val _signIn= MutableLiveData<PasswordResponse>()
    val signInlivedata: LiveData<PasswordResponse> = _signIn

    private val _propertyListMutable= MutableLiveData<SelectPropertyResponse>()
    val propertyListlivedata: LiveData<SelectPropertyResponse> = _propertyListMutable

    private val _profileMutable= MutableLiveData<ProfileResponse>()
    val profilelivedata: LiveData<ProfileResponse> = _profileMutable


    private val _welcomeScreenMutable= MutableLiveData<WelcomeScreenResponse>()
    val welcomeScreenlivedata: LiveData<WelcomeScreenResponse> = _welcomeScreenMutable

    private val _homeScreenMutable= MutableLiveData<HomeScreenResponse>()
    val homeScreenlivedata: LiveData<HomeScreenResponse> = _homeScreenMutable

    private val _ourGuestsnMutable= MutableLiveData<ForOurGuestResponse>()
    val ourGuestslivedata: LiveData<ForOurGuestResponse> = _ourGuestsnMutable

    private val _propertyDetailMutable= MutableLiveData<PropertyDetailResponse>()
    val propertyDetaillivedata: LiveData<PropertyDetailResponse> = _propertyDetailMutable


    private val _guestDetailMutable= MutableLiveData<GuestDetailResponse>()
    val guestDetaillivedata: LiveData<GuestDetailResponse> = _guestDetailMutable

    private val _serviceslMutable= MutableLiveData<ServiceResponse>()
    val serviceslivedata: LiveData<ServiceResponse> = _serviceslMutable

    private val _faqslMutable= MutableLiveData<FaqResponse>()
    val faqslivedata: LiveData<FaqResponse> = _faqslMutable

    private val _reviewslMutable= MutableLiveData<ReviewResponse>()
    val reviewslivedata: LiveData<ReviewResponse> = _reviewslMutable


    private val _logout= MutableLiveData<LogoutResponse>()
    val logoutlivedata: LiveData<LogoutResponse> = _logout


    @SuppressLint("SuspiciousIndentation")
    fun signin(code:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = SignInRequest(code)
                        val response=repository.signIn(request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _signIn.postValue(response.body())
                            } else {
                                val apiErrorResponse = PasswordResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _signIn.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = PasswordResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _signIn.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = PasswordResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _signIn.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getPropertyList(token:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {

                        val response=repository.getPropertiesList(token)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _propertyListMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = SelectPropertyResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _propertyListMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = SelectPropertyResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _propertyListMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = SelectPropertyResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _propertyListMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getProfile(token:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {

                        val response=repository.getProfile(token)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _profileMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = ProfileResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _profileMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ProfileResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _profileMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ProfileResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _profileMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getWelcomeScreen(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getWelcomeScreen(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _welcomeScreenMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = WelcomeScreenResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _welcomeScreenMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = WelcomeScreenResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _welcomeScreenMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = WelcomeScreenResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _welcomeScreenMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getFaqs(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getFaqs(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _faqslMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = FaqResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _faqslMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = FaqResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _faqslMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = FaqResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _faqslMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getReviews(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getReviews(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _reviewslMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = ReviewResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _reviewslMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ReviewResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _reviewslMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ReviewResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _reviewslMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getlogout(token:String,code:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = SignInRequest(code)
                        val response=repository.getlogout(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _logout.postValue(response.body())
                            } else {
                                val apiErrorResponse = LogoutResponse(false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _logout.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = LogoutResponse(false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _logout.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = LogoutResponse(false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _logout.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getHomeScreen(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getHomeScreen(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _homeScreenMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = HomeScreenResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _homeScreenMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = HomeScreenResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _homeScreenMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = HomeScreenResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _homeScreenMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getOurGuests(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getOurGuests(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _ourGuestsnMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = ForOurGuestResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _ourGuestsnMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ForOurGuestResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _ourGuestsnMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ForOurGuestResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _ourGuestsnMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getPropertyDetail(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getPropertyDetail(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _propertyDetailMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = PropertyDetailResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _propertyDetailMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = PropertyDetailResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _propertyDetailMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = PropertyDetailResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _propertyDetailMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getServices(token:String,property_id:String){
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        val request = PropertyRequest(property_id)
                        val response=repository.getServices(token,request)

                        if (response.isSuccessful) {
                            if (response.code() == 200) {
                                _serviceslMutable.postValue(response.body())
                            } else {
                                val apiErrorResponse = ServiceResponse(null,false,response.body()!!.message.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message = "Code Invalid"
                                _serviceslMutable.postValue(apiErrorResponse)
                            }
                        }else if (response.code() == 401) {
                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ServiceResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _serviceslMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }
                        }else {

                            val gson = GsonBuilder().create()
                            var mError = ApiError()
                            try {

                                mError = gson.fromJson(
                                    response.errorBody()!!.string(),
                                    ApiError::class.java
                                )
                                val apiErrorResponse = ServiceResponse(null,false,response.errorBody()!!.toString())
                                apiErrorResponse.status = false
                                apiErrorResponse.message =mError.errorMessage
                                _serviceslMutable.postValue(apiErrorResponse)

                            } catch (e: IOException) {

                                // handle failure to read error
                            }

                            // Handle API call failure
                        }
                    }catch (e:Exception){

                    }

                }}
        }

    fun getGuestDetail(token:String,property_id:String,guest_id:String){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val request = SingleGuestRequest(property_id,guest_id)
                    val response=repository.getGuestDetails(token,request)

                    if (response.isSuccessful) {
                        if (response.code() == 200) {
                            _guestDetailMutable.postValue(response.body())
                        } else {
                            val apiErrorResponse = GuestDetailResponse(null,false,response.body()!!.message.toString())
                            apiErrorResponse.status = false
                            apiErrorResponse.message = "Code Invalid"
                            _guestDetailMutable.postValue(apiErrorResponse)
                        }
                    }else if (response.code() == 401) {
                        val gson = GsonBuilder().create()
                        var mError = ApiError()
                        try {

                            mError = gson.fromJson(
                                response.errorBody()!!.string(),
                                ApiError::class.java
                            )
                            val apiErrorResponse = GuestDetailResponse(null,false,response.errorBody()!!.toString())
                            apiErrorResponse.status = false
                            apiErrorResponse.message =mError.errorMessage
                            _guestDetailMutable.postValue(apiErrorResponse)

                        } catch (e: IOException) {

                            // handle failure to read error
                        }
                    }else {

                        val gson = GsonBuilder().create()
                        var mError = ApiError()
                        try {

                            mError = gson.fromJson(
                                response.errorBody()!!.string(),
                                ApiError::class.java
                            )
                            val apiErrorResponse = GuestDetailResponse(null,false,response.errorBody()!!.toString())
                            apiErrorResponse.status = false
                            apiErrorResponse.message =mError.errorMessage
                            _guestDetailMutable.postValue(apiErrorResponse)

                        } catch (e: IOException) {

                            // handle failure to read error
                        }

                        // Handle API call failure
                    }
                }catch (e:Exception){

                }

            }}
    }
}