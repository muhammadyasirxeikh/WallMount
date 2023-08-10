package com.app.wallmount.controller.fragment.welcome

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
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentWelcomeBinding
import com.app.wallmount.repository.AppRepository
import com.app.wallmount.utils.Constants
import com.bumptech.glide.Glide


class WelcomeFragment : Fragment() {


    var binding:FragmentWelcomeBinding?=null
    lateinit var  mainViewModel: MainViewModel
    var mainActivity: MainActivity?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentWelcomeBinding.inflate(layoutInflater,container,false)

        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(Constants.TOKEN, "")
        val propertyId = sh.getString(Constants.PROPERTY_ID, "")

        getWelcomeScreen(token,propertyId)

        binding?.explore?.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_homeFragment)
        }

        return binding?.root
    }

    private fun getWelcomeScreen(token: String?, propertyId: String?) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getWelcomeScreen("Bearer $token",propertyId!!)
        mainViewModel.welcomeScreenlivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {

                    binding?.welcomeName?.text = "Welcome \n ${it.welcomeScreen?.guestName}"
                    binding?.mainLayout?.let { it1 ->
                        Glide.with(mainActivity!!)
                            .load(it.welcomeScreen?.welcomeImage)
                            .into(it1)
                    }
                    binding?.welcomeMessage?.text = "${it.welcomeScreen?.welcomeMessage}"
                    val sharedPreferences = mainActivity?.getSharedPreferences(Constants.PREFNAME,
                        Context.MODE_PRIVATE
                    )!!
                    val myEdit= sharedPreferences.edit()
                    myEdit.putString(Constants.GUEST_NAME, it.welcomeScreen?.guestName)
                    myEdit.putString(Constants.DEPARTURE_MESSAGE, it.welcomeScreen?.departureMessage)
                    myEdit.putString(Constants.DEPARTURE_IMAGE, it.welcomeScreen?.departureImage)
                    myEdit.putString(Constants.USER_F_NAME, it.welcomeScreen?.userName)
                    myEdit.apply()

                }catch (e:Exception){
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}