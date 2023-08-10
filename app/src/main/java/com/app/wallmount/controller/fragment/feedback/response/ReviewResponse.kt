package com.app.wallmount.controller.fragment.feedback.response

import com.google.gson.annotations.SerializedName

data class ReviewResponse(@SerializedName("reviews")
                          val reviews: Reviews?,
                          @SerializedName("status")
                          var status: Boolean = false,
                          @SerializedName("message")
                          var message: String = "")