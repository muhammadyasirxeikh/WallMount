package com.app.wallmount.controller.fragment.forOurGuestDetails.response

import com.google.gson.annotations.SerializedName

data class Ourguests(@SerializedName("updated_at")
                     val updatedAt: String = "",
                     @SerializedName("user_id")
                     val userId: String = "",
                     @SerializedName("description")
                     val description: String = "",
                     @SerializedName("created_at")
                     val createdAt: String = "",
                     @SerializedName("id")
                     val id: Int = 0,
                     @SerializedName("title")
                     val title: String = "",
                     @SerializedName("upload_images")
                     val uploadImages: List<String>?,
                     @SerializedName("property_id")
                     val propertyId: String = "")