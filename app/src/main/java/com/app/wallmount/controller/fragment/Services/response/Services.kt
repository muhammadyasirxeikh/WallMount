package com.app.wallmount.controller.fragment.Services.response

import com.google.gson.annotations.SerializedName

data class Services(@SerializedName("services")
                    val services: List<ServicesItem>?)