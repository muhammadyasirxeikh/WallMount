package com.app.wallmount.controller.fragment.faq.response

import com.google.gson.annotations.SerializedName

data class Faqs(@SerializedName("faqs")
                val faqs: List<FaqsItem>?)