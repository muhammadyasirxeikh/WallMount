package com.app.wallmount.controller.Weatherdataclass

import com.google.gson.annotations.SerializedName

data class City(@SerializedName("country")
                val country: String = "",
                @SerializedName("coord")
                val coord: Coord,
                @SerializedName("sunrise")
                val sunrise: Int = 0,
                @SerializedName("timezone")
                val timezone: Int = 0,
                @SerializedName("sunset")
                val sunset: Int = 0,
                @SerializedName("name")
                val name: String = "",
                @SerializedName("id")
                val id: Int = 0,
                @SerializedName("population")
                val population: Int = 0)