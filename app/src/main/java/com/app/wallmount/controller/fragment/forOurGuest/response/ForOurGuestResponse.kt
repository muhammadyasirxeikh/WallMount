package com.app.wallmount.controller.fragment.forOurGuest.response

import com.google.gson.annotations.SerializedName

data class ForOurGuestResponse(@SerializedName("our_guests")
                               val ourGuests: OurGuests?,
                               @SerializedName("status")
                               var status: Boolean = false,
                               @SerializedName("message")
                               var message: String = "")