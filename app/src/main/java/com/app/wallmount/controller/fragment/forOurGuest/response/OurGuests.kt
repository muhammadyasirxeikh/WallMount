package com.app.wallmount.controller.fragment.forOurGuest.response

import com.google.gson.annotations.SerializedName

data class OurGuests(@SerializedName("ourguests")
                     val ourguests: List<OurguestsItem>?)