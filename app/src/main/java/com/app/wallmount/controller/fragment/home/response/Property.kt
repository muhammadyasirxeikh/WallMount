package com.app.wallmount.controller.fragment.home.response

import com.google.gson.annotations.SerializedName

data class Property(@SerializedName("zipcode")
                    val zipcode: String = "",
                    @SerializedName("address")
                    val address: String = "",
                    @SerializedName("wifi_name")
                    val wifiName: String = "",
                    @SerializedName("city")
                    val city: String = "",
                    @SerializedName("buliding_name")
                    val bulidingName: String = "",
                    @SerializedName("clock_out_time")
                    val clockOutTime: String = "",
                    @SerializedName("name")
                    val name: String = "",
                    @SerializedName("clock_in_time")
                    val clockInTime: String = "",
                    @SerializedName("id")
                    val id: Int = 0,
                    @SerializedName("wifi_pwd")
                    val wifiPwd: String = "")