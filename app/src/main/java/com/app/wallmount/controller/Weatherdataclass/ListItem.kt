package com.app.wallmount.controller.Weatherdataclass

import com.google.gson.annotations.SerializedName

data class ListItem(@SerializedName("dt")
                    val dt: Int = 0,
                    @SerializedName("pop")
                    val pop: Double = 0.0,
                    @SerializedName("visibility")
                    val visibility: Int = 0,
                    @SerializedName("dt_txt")
                    val dtTxt: String = "",
                    @SerializedName("weather")
                    val weather: List<WeatherItem>?,
                    @SerializedName("main")
                    val main: Main,
                    @SerializedName("clouds")
                    val clouds: Clouds,
                    @SerializedName("sys")
                    val sys: Sys,
                    @SerializedName("wind")
                    val wind: Wind)