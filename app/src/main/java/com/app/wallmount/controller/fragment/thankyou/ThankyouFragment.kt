package com.app.wallmount.controller.fragment.thankyou

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.databinding.FragmentFaqBinding
import com.app.wallmount.databinding.FragmentThankyouBinding
import com.app.wallmount.utils.Constants
import com.bumptech.glide.Glide


class ThankyouFragment : Fragment() {

    var binding: FragmentThankyouBinding? = null
    var mainActivity: MainActivity? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThankyouBinding.inflate(layoutInflater, container, false)
        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val check_outTime = sh.getString(Constants.CHECK_OUT_TIME, "")
        val image = sh.getString(Constants.DEPARTURE_IMAGE, "")
        val message = sh.getString(Constants.DEPARTURE_MESSAGE, "")
        binding?.checkoutTimeTv?.text=check_outTime
        binding?.welcomeMessage?.text=message
        Glide.with(mainActivity!!)
            .load(image)
            .into(binding?.mainLayout!!)

        return binding?.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}