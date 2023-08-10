package com.app.wallmount.controller.fragment.faq


import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R

import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.viewModel.MainViewModel
import com.app.wallmount.controller.viewModel.MainViewModelFactory
import com.app.wallmount.databinding.FragmentFaqBinding
import com.app.wallmount.repository.AppRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target


class FaqFragment : Fragment() {
    var binding: FragmentFaqBinding? = null
    var mainActivity: MainActivity? = null
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFaqBinding.inflate(layoutInflater, container, false)
        val sh: SharedPreferences = mainActivity!!.getSharedPreferences(
            com.app.wallmount.utils.Constants.PREFNAME,
            Context.MODE_PRIVATE
        )
        val token = sh.getString(com.app.wallmount.utils.Constants.TOKEN, "")
        val propertyId = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")
        val user_name = sh.getString(com.app.wallmount.utils.Constants.USER_F_NAME, "")
        val user_number = sh.getString(com.app.wallmount.utils.Constants.USER_NUMBER, "")
        val user_email = sh.getString(com.app.wallmount.utils.Constants.USER_EMAIL, "")
        val user_image = sh.getString(com.app.wallmount.utils.Constants.PROPERTY_ID, "")
        binding?.contactTv?.setOnClickListener {
//            Toast.makeText(mainActivity,user_number+user_email+ "", Toast.LENGTH_SHORT).show()
            openCustomDialog(user_name.toString(),user_email.toString(), user_number.toString())
        }
        getFaqs(token,propertyId)

        return binding?.root
    }

    private fun getFaqs(token: String?, propertyId: String?) {
        val repository= AppRepository(mainActivity!!)

        mainViewModel= ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getFaqs("Bearer $token",propertyId!!)
        mainViewModel.faqslivedata.observe(viewLifecycleOwner, Observer{
            if (it.status) {
                try {

                    val layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
                    binding?.faqRecycler?.layoutManager = layoutManager
                    val adapter = FAQAdapter(it.faqs?.faqs!!)
                    binding?.faqRecycler?.adapter = adapter

                }catch (e:Exception){
                    Toast.makeText(mainActivity, "${e.message}", Toast.LENGTH_SHORT).show()
                }

            }else{

                Toast.makeText(mainActivity, it.message, Toast.LENGTH_SHORT).show()

            }
        })


    }

    private fun openCustomDialog(name:String,email:String,number:String) {
        // Create a custom dialog instance
        val customDialog = Dialog(mainActivity!!)
        customDialog.setContentView(R.layout.contact_host_dialog_layout)

        // Initialize dialog views
        val userImageView: ImageView = customDialog.findViewById(R.id.user_image)
        val userNameTextView: TextView = customDialog.findViewById(R.id.user_name)
        val phoneNumberTextView: TextView = customDialog.findViewById(R.id.phone_number)
        val emailTextView: TextView = customDialog.findViewById(R.id.email)

        // Load user image using Glide (assuming imageUrl is the image URL)
//        Glide.with(this).load(imageUrl).into<Target<Drawable>>(userImageView)

        // Set user name, phone number, and email
        userNameTextView.text = name
        phoneNumberTextView.text = number
        emailTextView.text = email

        // Find the close button in the dialog
        val closeButton: ImageView = customDialog.findViewById(R.id.close)

        // Set an OnClickListener to dismiss the dialog when the close button is clicked
        closeButton.setOnClickListener(View.OnClickListener { customDialog.dismiss() })

        // Show the custom dialog
        customDialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
}