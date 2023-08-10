package com.app.wallmount.controller.fragment.mapsAndNearBy

import android.annotation.SuppressLint
import android.content.Context
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.activity.MyApp
import com.app.wallmount.databinding.FragmentMapsAndNearByBinding
import com.app.wallmount.utils.MapConstant
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.Status
import com.google.android.gms.common.util.MapUtils
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import java.security.Permissions


class MapsAndNearBy : Fragment() , OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
    GoogleApiClient.OnConnectionFailedListener, LocationListener {

    var binding: FragmentMapsAndNearByBinding?=null
    var mainActivity: MainActivity?=null
    var latt=31.4762736
    var lng=74.245145
    private var mGoogleApiClient: GoogleApiClient? = null
    private var mLastLocation: Location? = null
    private var mCurrLocationMarker: Marker? = null
    private var mLocationRequest: LocationRequest? = null
    private var mMap: GoogleMap? = null
    var autocompleteSupportFragment1: AutocompleteSupportFragment? = null
    private lateinit var mapFragment: SupportMapFragment
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMapsAndNearByBinding.inflate(layoutInflater,container,false)

        getCurrentLocation()
        mapFragment = childFragmentManager.findFragmentById(R.id.mapView) as SupportMapFragment
        mapFragment.getMapAsync(this)
        if (!Places.isInitialized()) {
            mainActivity?.let { Places.initialize(it,"AIzaSyAzok945r987fv6DKkaBjtmd-EMp1R2G-Y") }
        }

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        autoCompleteSuggestion()


        binding?.property?.setOnClickListener {
            findNavController().navigate(R.id.action_mapsAndNearBy_to_propertyFragment)
        }
        binding?.localAttraction?.setOnClickListener {
            findNavController().navigate(R.id.action_mapsAndNearBy_to_localAttarctions)
        }
        binding?.services?.setOnClickListener {
            findNavController().navigate(R.id.action_mapsAndNearBy_to_servicesFragment)
        }
        return binding?.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private fun autoCompleteSuggestion() {
        // Initialize Autocomplete Fragments
        // from the main activity layout file
        if (autocompleteSupportFragment1 == null)
            autocompleteSupportFragment1 =
                childFragmentManager.findFragmentById(R.id.autocomplete_fragment1) as AutocompleteSupportFragment?

        // Information that we wish to fetch after typing
        // the location and clicking on one of the options
        autocompleteSupportFragment1!!.setPlaceFields(
            listOf(
                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER,
                Place.Field.LAT_LNG,
                Place.Field.OPENING_HOURS,
                Place.Field.RATING,
                Place.Field.USER_RATINGS_TOTAL
            )
        )


        // Display the fetched information after clicking on one of the options
        autocompleteSupportFragment1!!.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {
//                MapUtils.isMapRunning = false

                // Information about the place
                val name = place.name
                val address = place.address
                // val phone = place.phoneNumber.toString()
                val latlng = place.latLng
                val latitude = latlng?.latitude
                val longitude = latlng?.longitude

                val rating = place.rating
                val userRatings = place.userRatingsTotal

                mMap!!.clear()

                latlng?.let { moveCameraToSelectedPlace(place) }
                // add current location
                //   MapUtils.addMarker(originLatitude, originLongitude, mMap!!, mContext)
                // add search location
//                MapUtils.addMarker(latlng, address, mMap!!)
//
//                destinationLatitude = latitude!!
//                destinationLongitude = longitude!!


            }

            override fun onError(status: Status) {
//                MapUtils.isMapRunning = false
                Toast.makeText(
                    mainActivity,
                   " some Error occoured ${status.statusMessage}",
                    Toast.LENGTH_SHORT
                ).show()
                Log.v("TAG566", "Some error occurred : ${status.statusMessage}")
            }
        })
    }

    private fun moveCameraToSelectedPlace(place: Place) {
        val latLng = place.latLng
        latLng?.let {
            val locationName = place.name ?: ""
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            mMap?.addMarker(MarkerOptions().position(latLng).title(locationName))
        }
    }
    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap?.apply {
            mapType = GoogleMap.MAP_TYPE_NORMAL
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isZoomGesturesEnabled = true
            uiSettings.isCompassEnabled = true
        }

        //Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (MapConstant.constant.checkPermissions(mainActivity)) {
                buildGoogleApiClient()
                mMap!!.isMyLocationEnabled = true
                val currentLatLng = LatLng(latt, lng)
                mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                mMap?.addMarker(MarkerOptions().position(currentLatLng))
            } else {
                MapConstant.constant.requestLocationPermission(mainActivity)
            }
        } else {
            buildGoogleApiClient()
            mMap!!.isMyLocationEnabled = true
            val currentLatLng = LatLng(latt, lng)
            mMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
        }

    }
    @Synchronized
    private fun buildGoogleApiClient() {
        mGoogleApiClient = GoogleApiClient.Builder(mainActivity!!)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build()
        mGoogleApiClient!!.connect()
        Log.v("TAG2", "buildGoogleApiClient :538")
    }
    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {
        val locationManager = mainActivity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        val provider = locationManager!!.getBestProvider(Criteria(), true)

        val locations = locationManager.getLastKnownLocation(provider!!)
        val providerList = locationManager.allProviders
        if (null != locations && null != providerList && providerList.size > 0) {
            lng = locations.longitude
            latt = locations.latitude

        }
    }



    @SuppressLint("MissingPermission")
    override fun onConnected(p0: Bundle?) {
        mLocationRequest = LocationRequest()
        mLocationRequest!!.interval = 1000
        mLocationRequest!!.fastestInterval = 1000
        mLocationRequest!!.priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
        if (MapConstant.constant.checkPermissions(mainActivity)) {
            LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient!!,
                mLocationRequest!!, this
            )
        } else {
            MapConstant.constant.requestLocationPermission(mainActivity)
        }

    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("Not yet implemented")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("Not yet implemented")
    }

    override fun onLocationChanged(location: Location) {

        latt= location?.latitude!!
        lng= location?.longitude!!
    }

}