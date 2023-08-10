package com.app.wallmount.controller.fragment.feedback.response

import com.google.gson.annotations.SerializedName

data class ReviewsItem(@SerializedName("question")
                       val question: String = "",
                       @SerializedName("updated_at")
                       val updatedAt: String = "",
                       @SerializedName("user_id")
                       val userId: String = "",
                       @SerializedName("created_at")
                       val createdAt: String = "",
                       @SerializedName("id")
                       val id: Int = 0,
                       @SerializedName("property_id")
                       val propertyId: String = "")