package com.app.wallmount.controller.fragment.welcome

import com.google.gson.annotations.SerializedName

data class WelcomeScreen(@SerializedName("departure_image")
                         val departureImage: String = "",
                         @SerializedName("guest_name")
                         val guestName: String = "",
                         @SerializedName("updated_at")
                         val updatedAt: String = "",
                         @SerializedName("user_id")
                         val userId: String = "",
                         @SerializedName("user_name")
                         val userName: String = "",
                         @SerializedName("welcome_image")
                         val welcomeImage: String = "",
                         @SerializedName("departure_message")
                         val departureMessage: String = "",
                         @SerializedName("created_at")
                         val createdAt: String = "",
                         @SerializedName("id")
                         val id: Int = 0,
                         @SerializedName("welcome_message")
                         val welcomeMessage: String = "",
                         @SerializedName("property_id")
                         val propertyId: String = "")