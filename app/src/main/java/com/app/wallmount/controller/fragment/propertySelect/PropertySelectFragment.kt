package com.app.wallmount.controller.fragment.propertySelect

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentPropertySelectBinding
import com.app.wallmount.repository.AppRepository
import com.app.wallmount.utils.Constants


class PropertySelectFragment : Fragment() {
    lateinit var  mainViewModel: MainViewModel
    var binding:FragmentPropertySelectBinding?=null

    var mainActivity:MainActivity?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentPropertySelectBinding.inflate(layoutInflater,container,false)

        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(Constants.PREFNAME, MODE_PRIVATE)
        val token = sh.getString(Constants.TOKEN, "")

        if (token != null) {
            getPropertiesList(token)
        }
        return binding?.root
    }

    private fun getPropertiesList(token: String) {


        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getPropertyList("Bearer $token")
        mainViewModel.propertyListlivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {
                    if (it.propertiesList!=null){
                        val propertiesList=it.propertiesList
                        val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                        binding?.propetyRecycler?.layoutManager = layoutManager
                        val adapter = PropertySelectAdapter(propertiesList,mainActivity!!)
                        binding?.propetyRecycler?.adapter = adapter
                    }else{
                        Toast.makeText(mainActivity, "No Properties Found", Toast.LENGTH_SHORT).show()
                    }




                    findNavController().navigate(R.id.action_passwordFragment_to_propertySelectFragment)
                }catch (e:Exception){
//                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{

//                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}