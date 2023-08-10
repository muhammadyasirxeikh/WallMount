package com.app.wallmount.controller.fragment.property

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentPropertyBinding
import com.app.wallmount.repository.AppRepository
import com.app.wallmount.utils.Constants
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType
import com.smarteist.autoimageslider.SliderAnimations
import com.smarteist.autoimageslider.SliderView


class PropertyFragment : Fragment() {

    var binding:FragmentPropertyBinding?=null
    var mainActivity:MainActivity?=null
    lateinit var  mainViewModel: MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentPropertyBinding.inflate(layoutInflater,container,false)

//        getSliderImages()
        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")

        getPropertyDetail(token,propertyId)
        binding?.localAttraction?.setOnClickListener {
            findNavController().navigate(R.id.action_propertyFragment_to_localAttarctions)
        }
        binding?.maps?.setOnClickListener {
            findNavController().navigate(R.id.action_propertyFragment_to_mapsAndNearBy)
        }
        binding?.services?.setOnClickListener {
            findNavController().navigate(R.id.action_propertyFragment_to_servicesFragment)
        }
        return binding?.root
    }
    private fun getPropertyDetail(token: String?, propertyId: String?) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getPropertyDetail("Bearer $token",propertyId!!)
        mainViewModel.propertyDetaillivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {
                    binding?.wifiName?.setText("Wifi:\n ${it.property?.property?.wifiName}")
                    binding?.wifiPassword?.setText("Password:\n ${it.property?.property?.wifiPwd}")
                    binding?.propertyName?.setText("${it.property?.property?.name}")
                    binding?.propertyAddress?.setText("${it.property?.property?.address}")
                    it.property?.property?.houseImages?.let { it1 -> getSliderImages(it1) }
                    binding?.needToKnowTv?.setText(it.property?.property?.needToKnow)
                    binding?.aboutTv?.setText(it.property?.property?.aboutProperty)
                    val sharedPreferences = mainActivity?.getSharedPreferences(
                        Constants.PREFNAME,
                        Context.MODE_PRIVATE
                    )!!
                    val myEdit= sharedPreferences.edit()
                    myEdit.putString(Constants.CHECK_IN_TIME, it.property?.property?.clockInTime)
                    myEdit.putString(Constants.CHECK_OUT_TIME, it.property?.property?.clockOutTime)
                    myEdit.apply()

                }catch (e:Exception){
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })


    }
    private fun getSliderImages(imageUrls: List<String>) {
//        val imageUrls: List<String> = listOf(
//            "http://airtouch.theappkit.com/public//images/1691313200_64cf643065064.jpg",
//            "http://airtouch.theappkit.com/public//images/1691313200_64cf6430651a7.jpg"
//        )

        val sliderAdapter = SliderAdapter(mainActivity, imageUrls)

        binding?.imageSlider?.apply {
            setSliderAdapter(sliderAdapter)
            autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            setIndicatorAnimation(IndicatorAnimationType.WORM)
            setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION)
            autoCycleDirection = SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH
            indicatorSelectedColor = Color.WHITE
            indicatorUnselectedColor = Color.WHITE
            isAutoCycle = false
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}