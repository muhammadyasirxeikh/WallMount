package com.app.wallmount.controller.fragment.Services.response

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.databinding.FragmentServiceDetailBinding
import com.bumptech.glide.Glide


class ServiceDetailFragment : Fragment() {
    var binding: FragmentServiceDetailBinding?=null
    var mainActivity: MainActivity?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentServiceDetailBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        val id = arguments?.getString("id")
        val serviceName = arguments?.getString("service_name")
        val servicePrice = arguments?.getString("service_price")
        val description = arguments?.getString("description")
        val image = arguments?.getString("image")

        binding?.catName01?.text=serviceName
        binding?.serviceDetail?.text=description
        binding?.serviceCharges?.text="$$servicePrice.00"
        binding?.serviceImage?.let {
            Glide.with(mainActivity!!)
                .load(image)
                .into(it)
        }
        return binding?.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}