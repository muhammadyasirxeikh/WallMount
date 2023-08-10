package com.app.wallmount.controller.fragment.recomendationFragment.response

import com.google.gson.annotations.SerializedName

data class Recomendationresponse(@SerializedName("name")
                                 val name: String = "",
                                 @SerializedName("rating")
                                 val rating: String ="",
                                 @SerializedName("distance")
                                 val distance: String = "",
                                 @SerializedName("image")
                                 val image: Int = 0,
                                 @SerializedName("reviews")
                                 val reviews: String ="" )
