package com.app.wallmount.controller.fragment.Services.response

import com.google.gson.annotations.SerializedName

data class ServiceResponse(
    @SerializedName("services")
    val services: Services?,
    @SerializedName("status")
    var status: Boolean = false,
    @SerializedName("message")
    var message: String = "")