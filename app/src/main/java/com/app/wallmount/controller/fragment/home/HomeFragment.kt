package com.app.wallmount.controller.fragment.home


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.Weatherdataclass.ListItem
import com.app.wallmount.controller.Weatherdataclass.WeatherDataClass
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.adapter.forOurGuestMain.ForGuestAdapter
import com.app.wallmount.controller.adapter.recomendation.HomeRecomendationAdapter
import com.app.wallmount.controller.adapter.weadtherAdapter.CustomWeatherClass
import com.app.wallmount.controller.adapter.weadtherAdapter.WeatherAdapter
import com.app.wallmount.controller.apis.ApiCall
import com.app.wallmount.controller.apis.Constants
import com.app.wallmount.controller.fragment.home.response.OurguestsItem
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentHomeBinding
import com.app.wallmount.repository.AppRepository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class HomeFragment : Fragment() {


    var binding :FragmentHomeBinding?=null
    var mainActivity:MainActivity?=null
    private val apiKey = "efd14b316e8b0a033b6ba0f7dc858263"
    private val lat=31.4762736
    private val long=74.245145
    lateinit var  mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(layoutInflater,container,false)


        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")

        getHomeScreen(token,propertyId)

        setRecomendationRecycler()

        binding?.todayDate?.text = getCurrentFormattedDate()
        binding?.viewAllRecomendation?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_recomendationsFragment)
        }
        weatherDetails(lat,long)

        binding?.property?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_propertyFragment)
        }
        binding?.localAttraction?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_localAttarctions)
        }
        binding?.maps?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_mapsAndNearBy)
        }
        binding?.services?.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_servicesFragment)
        }

//        binding?.weatherTv?.text=getWeather(lat,long).toString()
        return binding?.root
    }
    private fun getHomeScreen(token: String?, propertyId: String?) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getHomeScreen("Bearer $token",propertyId!!)
        mainViewModel.homeScreenlivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {
                    binding?.wifiName?.setText("Wifi: ${it.home?.property?.wifiName}")
                    binding?.wifiPassword?.setText("Password: ${it.home?.property?.wifiPwd}")
                    binding?.propertyName?.setText("${it.home?.property?.name}")
                    binding?.propertyAddress?.setText("${it.home?.property?.address}")
                    it.home?.ourguests?.let { it1 -> setForGuestRecycler(it1) }
                    it.home?.ourguests?.let { it1 ->  setThingsToDoRecycler(it1) }
                }catch (e:Exception){
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })


    }
    private fun setForGuestRecycler(list:List<OurguestsItem>) {
//        val names: MutableList<String> = ArrayList()
//        names.add("Welcome to Brooklyn Slopes")
//        names.add("Home Away From Home")
//
//        val images: MutableList<Int> = java.util.ArrayList()
//        images.add(R.drawable.for_guest_1)
//        images.add(R.drawable.for_guest_2)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding?.forOurGuestRv?.layoutManager = layoutManager
        val adapter = ForGuestAdapter(mainActivity!!, list,mainActivity!!)
        binding?.forOurGuestRv?.adapter = adapter
    }

    private fun setThingsToDoRecycler(list:List<OurguestsItem>) {
//        val names: MutableList<String> = ArrayList()
//        names.add("How to get around")
//        names.add("Explore New York City")
//
//        val images: MutableList<Int> = java.util.ArrayList()
//        images.add(R.drawable.things_1)
//        images.add(R.drawable.things_2)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding?.thingsToKnowRv?.layoutManager = layoutManager
        val adapter = ForGuestAdapter(mainActivity!!,  list,mainActivity!!)
        binding?.thingsToKnowRv?.adapter = adapter
    }

    private fun setRecomendationRecycler() {
        val names: MutableList<String> = ArrayList()
        names.add("Harlem Shake")
        names.add("Explore New York")
        names.add("Ali's Cocktails")
        val rating: MutableList<String> = ArrayList()
        rating.add("4.5")
        rating.add("4.3")
        rating.add("4.9")
        val placeType: MutableList<String> = ArrayList()
        placeType.add("Resturant")
        placeType.add("Sightseeing")
        placeType.add("Bars")

        val images: MutableList<Int> = java.util.ArrayList()
        images.add(R.drawable.recomendation_3)
        images.add(R.drawable.recomendation_2)
        images.add(R.drawable.recomendation_1)
        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding?.recomendationRv?.layoutManager = layoutManager
        val adapter = HomeRecomendationAdapter(mainActivity!!, names,rating,placeType, images)
        binding?.recomendationRv?.adapter = adapter
    }

    private fun weatherDetails(latitude: Double, longitude: Double) {
        val mApi = Constants().getRetrofitInstance()
        val apiCall = mApi.create(ApiCall::class.java)

        apiCall.weatherDetails( latitude, longitude, "efd14b316e8b0a033b6ba0f7dc858263")
            .enqueue(object: Callback<WeatherDataClass> {
                override fun onResponse(call: Call<WeatherDataClass>, response: Response<WeatherDataClass>) {


                    if (response.isSuccessful && response.code() == 200){
                        val weatherData = response.body()
                        if (weatherData != null) {
                            Log.i(tag, "Weather:: if ${response.body()}")
//                            weatherDataList.clear()
//
                          var  weatherDataList: List<ListItem>? = weatherData.list
//
//                            customWeatherList.clear()
//
                            var customWeatherList: MutableList<CustomWeatherClass> = arrayListOf()
                            if (weatherDataList != null) {
                                for (item in weatherDataList){
                                    customWeatherList.add(
                                        CustomWeatherClass(item.dtTxt, item.dtTxt?.substring(11,19), item.main?.temp)
                                    )
                                }
                            }
//
//                            filteredWeatherDataList.clear()
//
                          var  filteredWeatherDataList = customWeatherList.filter { weather ->
                                weather.time == "21:00:00"
                            } as MutableList<CustomWeatherClass>
//
//
                           var weatherAdapter = WeatherAdapter(mainActivity!!, filteredWeatherDataList, weatherData)
                            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                            binding?.weatherRv?.layoutManager = layoutManager
                            binding?.weatherRv?.adapter = weatherAdapter
                        }

                    }else {
                        Log.i(tag, "Weather:: else ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<WeatherDataClass>, t: Throwable) {
                    Log.i(tag, "Weather:: onFailure ${t.message}")
                }
            })

    }
    fun getCurrentFormattedDate(): String {
        val currentDate = Date()
        val dateFormat = SimpleDateFormat("MMM dd", Locale.ENGLISH)
        return dateFormat.format(currentDate).toUpperCase()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}
