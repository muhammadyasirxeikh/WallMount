package com.app.wallmount.controller.fragment.propertySelect.response

import com.google.gson.annotations.SerializedName

data class PropertiesListItem(@SerializedName("name")
                              val name: String = "",
                              @SerializedName("id")
                              val id: Int = 0,
                              @SerializedName("featured_image")
                              val featuredImage: String = "")