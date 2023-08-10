package com.app.wallmount.controller.fragment.home.response

import com.google.gson.annotations.SerializedName

data class HomeScreenResponse(@SerializedName("home")
                              val home: Home?,
                              @SerializedName("status")
                              var status: Boolean = false,
                              @SerializedName("message")
                              var message: String? = "")