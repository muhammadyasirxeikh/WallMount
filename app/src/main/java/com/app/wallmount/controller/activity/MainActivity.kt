package com.app.wallmount.controller.activity

import android.Manifest
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import com.app.wallmount.R
import com.app.wallmount.controller.Weatherdataclass.ListItem
import com.app.wallmount.controller.Weatherdataclass.WeatherDataClass
import com.app.wallmount.controller.adapter.weadtherAdapter.CustomWeatherClass
import com.app.wallmount.controller.apis.ApiCall
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.ActivityMainBinding
import com.app.wallmount.repository.AppRepository
import com.app.wallmount.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity(), NavController.OnDestinationChangedListener  {

    private lateinit var navController: NavController
    var binding :ActivityMainBinding?=null
    lateinit var temp:String
    lateinit var tempfarhanhite:String
    private val lat=31.4762736
    private val long=74.245145

    lateinit var  mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        weatherDetails(lat,long)

        if (checkLocationPermission()) {
            // Permission is already granted
            // You can perform location-related tasks here
        } else {
            // Request location permission
            requestLocationPermission();
        }

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        binding?.homeIcon?.setOnClickListener {
            navController.popBackStack(R.id.homeFragment, false)
        }

        binding?.contact?.setOnClickListener {
            navController.navigate(R.id.faqFragment)
        }
        binding?.time?.text=getCurrentFormattedTime()
        navController.addOnDestinationChangedListener(this)

        binding?.powerOff?.setOnClickListener {
            val sh: SharedPreferences = getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE)
            val token = sh.getString(Constants.TOKEN, "")
            val code = sh.getString(Constants.USER_CODE, "")
//            Toast.makeText(this@MainActivity, "power off called$code $token", Toast.LENGTH_SHORT).show()
            getlogout(token.toString(),code.toString())
        }

        // Handle the initial state of the bottom_bar
        onDestinationChanged(navController, navController.currentDestination!!, null)
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        when (destination.id) {
            R.id.welcomeFragment,R.id.passwordFragment,R.id.propertySelectFragment  -> hideBottomBar()
            else -> showBottomBar()
        }
    }
    private fun getlogout(token:String,code: String) {


        val repository= AppRepository(this)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getlogout("Bearer $token",code)
        mainViewModel.logoutlivedata.observe(this, Observer {


            if (it.status) {
                try {
                    val sharedPreferences =getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE)!!
                    val myEdit= sharedPreferences.edit()
                    myEdit.clear().apply()
                    navController.navigate(R.id.passwordFragment)

                    Toast.makeText(this@MainActivity, "Logout Successfuly", Toast.LENGTH_SHORT).show()
                }catch (e:Exception){
                    Toast.makeText(this@MainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })



    }

    private fun checkLocationPermission(): Boolean {
        // Check if the location permission is granted
        return (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
    }

    private fun requestLocationPermission() {
        // Request location permission if not granted
        ActivityCompat.requestPermissions(
            this, arrayOf<String>(Manifest.permission.ACCESS_FINE_LOCATION),
            1001
        )
    }
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<String?>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1001) {
//            // Check if the permission is granted
//            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                // Permission granted, you can perform location-related tasks here
//            } else {
//                // Permission denied, handle this case (e.g., show a message)
//            }
//        }
//    }
    private fun showBottomBar() {
        binding?.bottomBar?.visibility = View.VISIBLE
    }
    override fun onBackPressed() {
        // Check if the current destination is the "Contact" fragment
        val currentDestinationId = navController.currentDestination?.id
        if (currentDestinationId == R.id.faqFragment) {

            navController.popBackStack()
        }else if (currentDestinationId == R.id.passwordFragment) {


        } else {
            super.onBackPressed() // Default behavior for other fragments
        }
    }
    private fun weatherDetails(latitude: Double, longitude: Double) {
        val mApi = com.app.wallmount.controller.apis.Constants().getRetrofitInstance()
        val apiCall = mApi.create(ApiCall::class.java)

        apiCall.weatherDetails( latitude, longitude, "efd14b316e8b0a033b6ba0f7dc858263")
            .enqueue(object: Callback<WeatherDataClass> {
                override fun onResponse(call: Call<WeatherDataClass>, response: Response<WeatherDataClass>) {


                    if (response.isSuccessful && response.code() == 200){
                        val weatherData = response.body()
                        if (weatherData != null) {
                            Log.i("tag", "Weather:: if ${response.body()}")
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
                           temp= kelvinToCelsius(filteredWeatherDataList[0].temp!!).toString()
                            var q=  kelvinToCelsius(filteredWeatherDataList[0].temp!!)*9/5
                            var t=q + 32


                            tempfarhanhite="$t°/$temp°"
                            binding?.temprature?.text=tempfarhanhite



                        //                            var weatherAdapter = WeatherAdapter(mainActivity!!, filteredWeatherDataList, weatherData)
//                            val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
//                            binding?.weatherRv?.layoutManager = layoutManager
//                            binding?.weatherRv?.adapter = weatherAdapter
                        }

                    }else {
                        Log.i("tag", "Weather:: else ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<WeatherDataClass>, t: Throwable) {
                    Log.i("tag", "Weather:: onFailure ${t.message}")
                }
            })

    }
    override fun onPause() {


//        val currentDestinationId = navController.currentDestination?.id
//        if (currentDestinationId == R.id.homeFragment) {
//            Toast.makeText(this@MainActivity, "you cant go back", Toast.LENGTH_SHORT).show()
//        }else{
            super.onPause()
//        }

        // This method is called when the activity is about to lose focus.
        // You can put code here to handle the home button press event.
    }
      private fun kelvinToCelsius(kelvin: Double): Int {
        return (kelvin - 273.15).toInt()
    }
    private fun hideBottomBar() {
        binding?.bottomBar?.visibility = View.GONE
    }
    fun getCurrentFormattedTime(): String {
        val currentTime = Date()
        val timeFormat = SimpleDateFormat("h:mm a", Locale.ENGLISH)
        return timeFormat.format(currentTime)
    }
}