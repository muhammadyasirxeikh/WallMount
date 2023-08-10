package com.app.wallmount.controller.fragment.welcome

import com.google.gson.annotations.SerializedName

data class WelcomeScreenResponse(@SerializedName("welcome_screen")
                                 val welcomeScreen: WelcomeScreen?,
                                 @SerializedName("status")
                                 var status: Boolean = false,
                                 @SerializedName("message")
                                 var message: String = "")