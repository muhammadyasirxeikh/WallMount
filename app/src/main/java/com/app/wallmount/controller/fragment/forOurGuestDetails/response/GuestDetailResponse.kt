package com.app.wallmount.controller.fragment.forOurGuestDetails.response

import com.google.gson.annotations.SerializedName

data class GuestDetailResponse(@SerializedName("single_guest")
                               var singleGuest: SingleGuest?,
                               @SerializedName("status")
                               var status: Boolean = false,
                               @SerializedName("message")
                               var message: String = "")