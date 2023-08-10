package com.app.wallmount.controller.fragment.recomendationFragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.recomendationFragment.response.Recomendationresponse
import com.app.wallmount.databinding.FragmentRecomendationsBinding


class RecomendationsFragment : Fragment() {


    var binding:FragmentRecomendationsBinding?=null
    var list:MutableList<Recomendationresponse>?=null
    var mainActivity: MainActivity?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentRecomendationsBinding.inflate(layoutInflater,container,false)


        list= arrayListOf()
        list?.add(Recomendationresponse("Lucky House Kitchen","4.5","1.5 mi",R.drawable.recomendation_3,"(200+)"))
        list?.add(Recomendationresponse("Lucky House Kitchen","4.5","1.5 mi",R.drawable.recomendation_3,"(200+)"))
        list?.add(Recomendationresponse("Lucky House Kitchen","4.5","1.5 mi",R.drawable.recomendation_3,"(200+)"))
        list?.add(Recomendationresponse("Lucky House Kitchen","4.5","1.5 mi",R.drawable.recomendation_3,"(200+)"))
        list?.add(Recomendationresponse("Lucky House Kitchen","4.5","1.5 mi",R.drawable.recomendation_3,"(200+)"))


        val layoutManager = GridLayoutManager(mainActivity,1)
        binding?.resturantRecomendationRv?.layoutManager = layoutManager
        val adapter = ResturanrRecomendationAdapter(mainActivity!!, list!!)
        binding?.resturantRecomendationRv?.adapter = adapter


        return binding?.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}