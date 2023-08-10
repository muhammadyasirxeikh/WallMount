package com.app.wallmount.controller.fragment.faq.response

import com.google.gson.annotations.SerializedName

data class FaqResponse(@SerializedName("faqs")
                       val faqs: Faqs?,
                       @SerializedName("status")
                       var status: Boolean = false,
                       @SerializedName("message")
                       var message: String = "")