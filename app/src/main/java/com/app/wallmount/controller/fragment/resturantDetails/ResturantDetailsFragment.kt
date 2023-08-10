package com.app.wallmount.controller.fragment.resturantDetails

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.forOurGuestDetails.PropertyPictureAdapter
import com.app.wallmount.databinding.FragmentPropertyBinding
import com.app.wallmount.databinding.FragmentResturantDetailsBinding


class ResturantDetailsFragment : Fragment() {

    var binding: FragmentResturantDetailsBinding?=null
    var mainActivity: MainActivity?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentResturantDetailsBinding.inflate(layoutInflater,container,false)
        // Inflate the layout for this fragment
        setupPictureAdapter()
        return binding?.root
    }

    private fun setupPictureAdapter() {
        val images: MutableList<Int> = java.util.ArrayList()
        images.add(R.drawable.recomendation_3)
        images.add(R.drawable.recomendation_2)
        images.add(R.drawable.recomendation_1)
        images.add(R.drawable.recomendation_3)
        val imageUrls: List<String> = listOf(
            "http://airtouch.theappkit.com/public//images/1691313200_64cf643065064.jpg",
            "http://airtouch.theappkit.com/public//images/1691313200_64cf6430651a7.jpg"
        )

        val layoutManager = GridLayoutManager(mainActivity, 4)
        binding?.pictureRv?.layoutManager = layoutManager
        val adapter = PropertyPictureAdapter(mainActivity!!, imageUrls)
        binding?.pictureRv?.adapter = adapter
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}