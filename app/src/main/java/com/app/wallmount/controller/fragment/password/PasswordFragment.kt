package com.app.wallmount.controller.fragment.password

import android.app.AlertDialog
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentPasswordBinding
import com.app.wallmount.repository.AppRepository
import com.app.wallmount.utils.Constants
import dmax.dialog.SpotsDialog


class PasswordFragment : Fragment() {
    var mainActivity:MainActivity?=null
    var binding:FragmentPasswordBinding?=null
    lateinit var  mainViewModel: MainViewModel
    private var progressDialog: AlertDialog? = null

    private val passwordViews = arrayOf(R.id.p1, R.id.p2, R.id.p3, R.id.p4, R.id.p5, R.id.p6)
    private val enteredPassword = StringBuilder()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progressDialog = SpotsDialog.Builder()
            .setContext(mainActivity)
            .setCancelable(false)
            .setTheme(R.style.Custom)
            .build()

        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE)
        val user_id = sh.getString(Constants.USER_ID, "")

        if (!user_id.equals("")){
            findNavController().navigate(R.id.action_passwordFragment_to_propertySelectFragment)

        }





        binding=FragmentPasswordBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment

        binding?.tv0?.setOnClickListener { onNumberClicked("0") }
        binding?.tv1?.setOnClickListener { onNumberClicked("1") }
        binding?.tv2?.setOnClickListener { onNumberClicked("2") }
        binding?.tv3?.setOnClickListener { onNumberClicked("3") }
        binding?.tv4?.setOnClickListener { onNumberClicked("4") }
        binding?.tv5?.setOnClickListener { onNumberClicked("5") }
        binding?.tv6?.setOnClickListener { onNumberClicked("6") }
        binding?.tv7?.setOnClickListener { onNumberClicked("7") }
        binding?.tv8?.setOnClickListener { onNumberClicked("8") }
        binding?.tv9?.setOnClickListener { onNumberClicked("9") }
        binding?.clear?.setOnClickListener { onClearClicked() }
        return binding?.root
    }
    private fun onNumberClicked(number: String) {
        if (enteredPassword.length < 6) {
            // Append the clicked number to the entered password
            enteredPassword.append(number)
            updatePasswordViews()
            if (enteredPassword.length == 6) {
                // Call the API when 6 digits are entered
                progressDialog?.show()
                callApiWithPassword(enteredPassword.toString())
            }
        }
    }

    private fun callApiWithPassword(code: String) {


        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.signin(code)
        mainViewModel.signInlivedata.observe(viewLifecycleOwner,Observer{
            if (it.status) {
                try {

                    val sharedPreferences = mainActivity?.getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE)!!
                    val myEdit= sharedPreferences.edit()
                    myEdit.putString(Constants.TOKEN, it.payload?.token)
                    myEdit.putString(Constants.USER_ID, it.payload?.userId.toString())
                    myEdit.putString(Constants.USER_CODE, code)
                    myEdit.apply()
                    getProfile(it.payload?.token.toString())


                }catch (e:Exception){
                    progressDialog?.dismiss()
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{
                progressDialog?.dismiss()
                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })



    }
    private fun getProfile(token: String) {


        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this,MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getProfile("Bearer $token")
        mainViewModel.profilelivedata.observe(viewLifecycleOwner,Observer{
            if (it.status) {
                try {
                    progressDialog?.dismiss()
                    val sharedPreferences = mainActivity?.getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE)!!
                    val myEdit= sharedPreferences.edit()
                    myEdit.putString(Constants.USER_F_NAME, it.user?.fName)
                    myEdit.putString(Constants.USER_L_NAME, it.user?.lName)
                    myEdit.putString(Constants.USER_EMAIL, it.user?.email)
                    myEdit.putString(Constants.USER_ROLE, it.user?.role)
                    myEdit.putString(Constants.USER_NUMBER, it.user?.number)
                    myEdit.putString(Constants.USER_CODE, it.user?.code)
                    myEdit.apply()

//                    Toast.makeText(mainActivity, "${it.user?.fName}${it.user?.lName}${it.user?.lName}${it.user?.email} ", Toast.LENGTH_SHORT).show()

                    findNavController().navigate(R.id.action_passwordFragment_to_propertySelectFragment)
                }catch (e:Exception){
                    progressDialog?.dismiss()
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{
                progressDialog?.dismiss()
                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })



    }


    private fun onClearClicked() {
        // Clear the last entered number from the password
        if (enteredPassword.isNotEmpty()) {
            enteredPassword.deleteCharAt(enteredPassword.length - 1)
            updatePasswordViews()
        }
    }

    private fun updatePasswordViews() {
        // Update the color of password ImageViews based on the entered password length
        for (i in 0 until passwordViews.size) {
            view?.findViewById<ImageView>(passwordViews[i])?.apply {
                if (i < enteredPassword.length) {
                    setImageResource(R.drawable.colored_circle)
                } else {
                    setImageResource(R.drawable.white_circle)
                }
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}