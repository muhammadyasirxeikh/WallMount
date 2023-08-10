package com.app.wallmount.controller.fragment.feedback

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.faq.FAQAdapter
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentFeedBackBinding
import com.app.wallmount.databinding.FragmentThankyouBinding
import com.app.wallmount.repository.AppRepository


class FeedBackFragment : Fragment() {

    var binding: FragmentFeedBackBinding? = null
    var mainActivity: MainActivity? = null
    lateinit var mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentFeedBackBinding.inflate(layoutInflater,container,false)


        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")

        getReviews(token,propertyId)


        return binding?.root
    }

    private fun getReviews(token: String?, propertyId: String?) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getReviews("Bearer $token",propertyId!!)
        mainViewModel.reviewslivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {

                    val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    binding?.reviewRecycler?.layoutManager = layoutManager
                    val adapter = ReviewAdapter(it.reviews?.reviews!!,)
                    binding?.reviewRecycler?.adapter = adapter

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