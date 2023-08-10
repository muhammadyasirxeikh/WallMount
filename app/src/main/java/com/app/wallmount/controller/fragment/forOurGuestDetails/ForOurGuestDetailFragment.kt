package com.app.wallmount.controller.fragment.forOurGuestDetails

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentForOurGuestDetailBinding
import com.app.wallmount.repository.AppRepository
import com.bumptech.glide.Glide


class ForOurGuestDetailFragment : Fragment() {

    var binding:FragmentForOurGuestDetailBinding?=null
    var mainActivity: MainActivity?=null
    lateinit var  mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentForOurGuestDetailBinding.inflate(layoutInflater,container,false)
        val id = arguments?.getString("guest_id")
        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")
        val guestName = sh.getString(com.app.wallmount.utils.Constants.GUEST_NAME, "")

        setupPictureAdapter()
        getPropertyDetail(token,propertyId,id.toString(),guestName)
        return binding?.root
    }

    private fun setupPictureAdapter() {
        val images: MutableList<Int> = java.util.ArrayList()
        images.add(R.drawable.property_1)
        images.add(R.drawable.property_2)
        images.add(R.drawable.property_3)
        images.add(R.drawable.property_4)



    }
    private fun getPropertyDetail(
        token: String?,
        propertyId: String?,
        guest_id: String,
        guestName: String?
    ) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getGuestDetail("Bearer $token",propertyId!!,guest_id)
        mainViewModel.guestDetaillivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {
                    binding?.propertyName?.setText(it.singleGuest?.ourguests?.title)
                    binding?.catName01?.setText("Hello $guestName")
                    binding?.serviceDetail?.setText(it.singleGuest?.ourguests?.description)
                    Glide.with(mainActivity!!)
                        .load(it.singleGuest?.ourguests?.uploadImages?.get(0))
                        .into(binding?.serviceImage!!)

                    binding?.serviceImage?.scaleType = ImageView.ScaleType.FIT_XY

                    val layoutManager = GridLayoutManager(mainActivity, 4)
                    binding?.pictureRv?.layoutManager = layoutManager
                    val adapter = PropertyPictureAdapter(mainActivity!!, it.singleGuest?.ourguests?.uploadImages!!)
                    binding?.pictureRv?.adapter = adapter

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