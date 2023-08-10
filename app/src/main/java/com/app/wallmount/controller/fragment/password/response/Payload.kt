package com.app.wallmount.controller.fragment.password.response

import com.google.gson.annotations.SerializedName

data class Payload(@SerializedName("user_id")
                   val userId: Int = 0,
                   @SerializedName("token")
                   val token: String = "")