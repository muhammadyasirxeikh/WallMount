package com.app.wallmount.controller.fragment.Services.response

import com.google.gson.annotations.SerializedName

data class ServicesItem(@SerializedName("image")
                        val image: String = "",
                        @SerializedName("service_price")
                        val servicePrice: String = "",
                        @SerializedName("updated_at")
                        val updatedAt: String = "",
                        @SerializedName("user_id")
                        val userId: String = "",
                        @SerializedName("service_name")
                        val serviceName: String = "",
                        @SerializedName("description")
                        val description: String = "",
                        @SerializedName("created_at")
                        val createdAt: String = "",
                        @SerializedName("id")
                        val id: Int = 0,
                        @SerializedName("property_id")
                        val propertyId: String = "")