package com.app.wallmount.controller.fragment.logout

import com.google.gson.annotations.SerializedName

data class LogoutResponse(@SerializedName("status")
                          var status: Boolean = false,
                          @SerializedName("message")
                          var message: String = ""
                          )