package com.app.wallmount.controller.fragment.home.response

import com.google.gson.annotations.SerializedName

data class Home(@SerializedName("property")
                val property: Property,
                @SerializedName("ourguests")
                val ourguests: List<OurguestsItem>?)