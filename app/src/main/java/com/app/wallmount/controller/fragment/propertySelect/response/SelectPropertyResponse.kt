package com.app.wallmount.controller.fragment.propertySelect.response

import com.google.gson.annotations.SerializedName

data class SelectPropertyResponse(@SerializedName("properties_list")
                                  val propertiesList: List<PropertiesListItem>?,
                                  @SerializedName("status")
                                  var status: Boolean = false,
                                  @SerializedName("message")
                                  var message: String = "")