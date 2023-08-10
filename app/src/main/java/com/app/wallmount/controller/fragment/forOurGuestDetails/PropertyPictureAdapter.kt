package com.app.wallmount.controller.fragment.forOurGuestDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R


import com.bumptech.glide.Glide

class PropertyPictureAdapter(ctx: Context, private val images: List<String>) : RecyclerView.Adapter<PropertyPictureAdapter.ViewHolder>() {

    private val context: Context = ctx

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View = layoutInflater.inflate(R.layout.property_picture_itemview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val imgUrl = images[position]

        // Load image using Glide from the URL
        Glide.with(context)
            .load(imgUrl)
            .into(holder.img)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.image_property)
    }
}


