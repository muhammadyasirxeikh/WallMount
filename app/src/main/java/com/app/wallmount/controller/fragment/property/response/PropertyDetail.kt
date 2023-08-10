package com.app.wallmount.controller.fragment.property.response

import com.google.gson.annotations.SerializedName

data class PropertyDetail(@SerializedName("address")
                          val address: String = "",
                          @SerializedName("city")
                          val city: String = "",
                          @SerializedName("created_at")
                          val createdAt: String = "",
                          @SerializedName("featured_image")
                          val featuredImage: String = "",
                          @SerializedName("wifi_pwd")
                          val wifiPwd: String = "",
                          @SerializedName("zipcode")
                          val zipcode: String = "",
                          @SerializedName("house_images")
                          val houseImages: List<String>?,
                          @SerializedName("wifi_name")
                          val wifiName: String = "",
                          @SerializedName("updated_at")
                          val updatedAt: String = "",
                          @SerializedName("user_id")
                          val userId: String = "",
                          @SerializedName("buliding_name")
                          val bulidingName: String = "",
                          @SerializedName("clock_out_time")
                          val clockOutTime: String = "",
                          @SerializedName("name")
                          val name: String = "",
                          @SerializedName("need_to_know")
                          val needToKnow: String = "",
                          @SerializedName("clock_in_time")
                          val clockInTime: String = "",
                          @SerializedName("id")
                          val id: Int = 0,
                          @SerializedName("about_property")
                          val aboutProperty: String = "")