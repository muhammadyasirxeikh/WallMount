package com.app.wallmount.controller.fragment.property.response

import com.google.gson.annotations.SerializedName

data class PropertyDetailResponse(@SerializedName("property")
                                  val property: Property?,
                                  @SerializedName("status")
                                  var status: Boolean = false,

                                  @SerializedName("message")
                                  var message: String = "")