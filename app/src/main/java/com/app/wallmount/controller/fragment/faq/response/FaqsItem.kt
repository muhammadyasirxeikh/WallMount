package com.app.wallmount.controller.fragment.faq.response

import com.google.gson.annotations.SerializedName

data class FaqsItem(@SerializedName("question")
                    val question: String = "",
                    @SerializedName("answer")
                    val answer: String = "",
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