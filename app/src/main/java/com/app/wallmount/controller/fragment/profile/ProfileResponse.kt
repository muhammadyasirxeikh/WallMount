package com.app.wallmount.controller.fragment.profile

import com.google.gson.annotations.SerializedName

data class ProfileResponse(@SerializedName("user")
                           val user: User?,
                           @SerializedName("status")
                           var status: Boolean = false,
                           @SerializedName("message")
                           var message: String = "")