package com.app.wallmount.controller.fragment.forOurGuest

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
import androidx.recyclerview.widget.GridLayoutManager
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.recomendationFragment.ResturanrRecomendationAdapter
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentForOurGuestRecyclerBinding
import com.app.wallmount.repository.AppRepository


class ForOurGuestRecyclerFragment : Fragment() {
    var binding: FragmentForOurGuestRecyclerBinding?=null
    var mainActivity: MainActivity?=null
    lateinit var  mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentForOurGuestRecyclerBinding.inflate(layoutInflater,container,false)

        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")


        getOurGuests(token,propertyId)

        return binding?.root
    }


    private fun getOurGuests(token: String?, propertyId: String?) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getOurGuests("Bearer $token",propertyId!!)
        mainViewModel.ourGuestslivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {
                    val list=it.ourGuests?.ourguests
                    val layoutManager = GridLayoutManager(mainActivity,1)
                    binding?.forOurRv?.layoutManager = layoutManager
                    val adapter = ForOurGuestRecyclerAdapter(mainActivity!!, list!!)
                    binding?.forOurRv?.adapter = adapter

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