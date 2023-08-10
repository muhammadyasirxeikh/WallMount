package com.app.wallmount.controller.fragment.feedback.response

import com.google.gson.annotations.SerializedName

data class Reviews(@SerializedName("reviews")
                   val reviews: List<ReviewsItem>?)