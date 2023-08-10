package com.app.wallmount.controller.fragment.password.response

import com.google.gson.annotations.SerializedName

data class PasswordResponse(@SerializedName("payload")
                            val payload: Payload?,
                            @SerializedName("status")
                            var status: Boolean = false,
                            @SerializedName("message")
                            var message: String = "")