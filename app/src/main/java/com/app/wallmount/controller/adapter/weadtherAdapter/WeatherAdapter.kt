package com.app.wallmount.controller.adapter.weadtherAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.Weatherdataclass.WeatherDataClass

import com.app.wallmount.databinding.WeatherLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

class WeatherAdapter(
    private val context: Context,
    private val weatherDataList: MutableList<CustomWeatherClass>,
    private val weatherData: WeatherDataClass
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private val tag = "NotificationAdapter"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = WeatherLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(mBinding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val weather = weatherDataList[position]

        var temp_far: Int = Integer.parseInt(weather.temp?.let { kelvinToCelsius(it) }.toString())
       var q= temp_far*9/5
        var t=q + 32
        holder.mBinding.tvDay.text = getDayNameFromDate(weather.date.toString())
        holder.mBinding.tvTemp.text = "$t°/${weather.temp?.let { kelvinToCelsius(it) }}°"
        holder.mBinding.imageView2.setBackgroundResource(R.drawable.sun_new)


    }

    override fun getItemCount(): Int {
        return weatherDataList.size
    }

    class ViewHolder(val mBinding: WeatherLayoutBinding) : RecyclerView.ViewHolder(mBinding.root)

    private fun kelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }

    private fun getDayNameFromDate(dateString: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = format.parse(dateString)
        val calendar = Calendar.getInstance()
        calendar.time = date
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

        val daysOfWeek = arrayOf(
            "Sun", "Mon", "Tues", "Wed", "Thu", "Fri", "Sat"
        )

        return daysOfWeek[dayOfWeek - 1]
    }
}
data class CustomWeatherClass(var date: String? = null, var time: String? = null, var temp: Double? = null)