package com.app.wallmount.controller.fragment.property.response

import com.google.gson.annotations.SerializedName

data class Property(@SerializedName("property")
                    val property: PropertyDetail
)