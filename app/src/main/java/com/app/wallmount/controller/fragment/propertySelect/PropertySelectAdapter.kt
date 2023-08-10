package com.app.wallmount.controller.fragment.propertySelect

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.propertySelect.response.PropertiesListItem
import com.app.wallmount.utils.Constants
import com.bumptech.glide.Glide

class PropertySelectAdapter(private val items: List<PropertiesListItem>, private  val mainActivity: MainActivity) :
    RecyclerView.Adapter<PropertySelectAdapter.ViewHolder>() {

    private var selectedPosition = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.property_select_itemview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
        holder.radioButton.isChecked = position == selectedPosition

        holder.radioButton.setOnClickListener {
            val sharedPreferences =mainActivity.getSharedPreferences(
                Constants.PREFNAME,
                Context.MODE_PRIVATE
            )
            val myEdit= sharedPreferences.edit()
            myEdit.putString(Constants.PROPERTY_ID, item.id.toString())
            myEdit.putString(Constants.PROPERTY_NAME, item.name)
            myEdit.apply()
            Navigation.findNavController(it).navigate(R.id.action_propertySelectFragment_to_welcomeFragment)
        }
    }

    override fun getItemCount(): Int = items.size

    fun getSelectedProperty(): PropertiesListItem? {
        return if (selectedPosition != -1) {
            items[selectedPosition]
        } else null
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.property_textView)
        private val imageView: ImageView = itemView.findViewById(R.id.property_imageView)
        val radioButton: RadioButton = itemView.findViewById(R.id.radioButton)

        fun bind(property: PropertiesListItem) {
            textView.text = property.name
            Glide.with(mainActivity)
                .load(property.featuredImage)
                .into(imageView)

            // Set click listener for RadioButton
            radioButton.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    selectedPosition = adapterPosition
                    notifyDataSetChanged()
                }
            }
        }
    }
}
