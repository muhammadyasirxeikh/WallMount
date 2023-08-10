package com.app.wallmount.controller.fragment.forOurGuestDetails.response

import com.google.gson.annotations.SerializedName

data class SingleGuest(@SerializedName("ourguests")
                       val ourguests: Ourguests
)