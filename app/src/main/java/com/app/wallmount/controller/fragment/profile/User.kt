package com.app.wallmount.controller.fragment.profile

import com.google.gson.annotations.SerializedName

data class User(@SerializedName("number")
                val number: String = "",
                @SerializedName("role")
                val role: String = "",
                @SerializedName("code")
                val code: String = "",
                @SerializedName("updated_at")
                val updatedAt: String = "",
                @SerializedName("l_name")
                val lName: String = "",
                @SerializedName("f_name")
                val fName: String = "",
                @SerializedName("created_at")
                val createdAt: String = "",
                @SerializedName("email_verified_at")
                val emailVerifiedAt: String = "null",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("email")
                val email: String = "")