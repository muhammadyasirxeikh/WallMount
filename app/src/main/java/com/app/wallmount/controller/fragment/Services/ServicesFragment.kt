package com.app.wallmount.controller.fragment.Services

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.Services.response.ServiceResponse
import com.app.wallmount.controller.fragment.Services.response.ServicesItem
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentServicesBinding
import com.app.wallmount.repository.AppRepository


class ServicesFragment : Fragment() {
    var binding: FragmentServicesBinding? = null
    var mainActivity: MainActivity? = null
    lateinit var mainViewModel: MainViewModel
    var list: MutableList<ServiceResponse>? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(layoutInflater, container, false)


//        list= arrayListOf()
//        list?.add(ServiceResponse("Dry Cleaning","$25",R.drawable.service_1))
//        list?.add(ServiceResponse("Laundree","$5",R.drawable.service_2))
//        list?.add(ServiceResponse("Taxi","$10",R.drawable.service_3))
//        list?.add(ServiceResponse("Trip","$35",R.drawable.service_4))
//        list?.add(ServiceResponse("Health","$20",R.drawable.service_5))
//        list?.add(ServiceResponse("Beauty","$15",R.drawable.service_6))

        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")

        getServices(token, propertyId)







        binding?.property?.setOnClickListener {
            findNavController().navigate(R.id.action_servicesFragment_to_propertyFragment)
        }
        binding?.localAttraction?.setOnClickListener {
            findNavController().navigate(R.id.action_servicesFragment_to_localAttarctions)
        }
        binding?.maps?.setOnClickListener {
            findNavController().navigate(R.id.action_servicesFragment_to_mapsAndNearBy)
        }

        return binding?.root
    }

    private fun getServices(token: String?, propertyId: String?) {
        val repository = AppRepository(mainActivity!!)

        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getServices("Bearer $token", propertyId!!)
        mainViewModel.serviceslivedata.observe(viewLifecycleOwner, Observer {
            if (it.status) {
                try {
                    val servicesList = it.services?.services


                    val layoutManager = GridLayoutManager(mainActivity, 2)
                    binding?.serviceRecycler?.layoutManager = layoutManager
                    val adapter = ServiceAdapter(
                        mainActivity!!,
                        servicesList as MutableList<ServicesItem>
                    )
                    binding?.serviceRecycler?.adapter = adapter

                } catch (e: Exception) {
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            } else {

                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}