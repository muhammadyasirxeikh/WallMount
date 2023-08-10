package com.app.wallmount.controller.fragment.localAttractions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.recomendationFragment.response.Recomendationresponse
import com.app.wallmount.databinding.FragmentLocalAttarctionsBinding


class LocalAttarctions : Fragment() {

    var binding: FragmentLocalAttarctionsBinding?=null
    var list:MutableList<Recomendationresponse>?=null
    var mainActivity: MainActivity?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentLocalAttarctionsBinding.inflate(layoutInflater,container,false)

        list= arrayListOf()
        list?.add(Recomendationresponse("Shopping in NY","","1.5 mi",R.drawable.explore_1,""))
        list?.add(Recomendationresponse("Brooklyn Bridge","","1.5 mi",R.drawable.explore_2,""))
        list?.add(Recomendationresponse("Times Square","","1.5 mi",R.drawable.explore_3,""))
        list?.add(Recomendationresponse("Statue of liberty","","1.5 mi",R.drawable.explore_4,""))

        val layoutManager = GridLayoutManager(mainActivity,2)
        binding?.exploreRecycler?.layoutManager = layoutManager
        val adapter = LocalAttractionAdapter(mainActivity!!, list!!)
        binding?.exploreRecycler?.adapter = adapter

        binding?.property?.setOnClickListener {
            findNavController().navigate(R.id.action_localAttarctions_to_propertyFragment)
        }

        binding?.maps?.setOnClickListener {
            findNavController().navigate(R.id.action_localAttarctions_to_mapsAndNearBy)
        }
        binding?.services?.setOnClickListener {
            findNavController().navigate(R.id.action_localAttarctions_to_servicesFragment)
        }
        return binding?.root
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

}