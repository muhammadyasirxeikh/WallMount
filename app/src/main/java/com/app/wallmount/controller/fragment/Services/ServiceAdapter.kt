package com.app.wallmount.controller.fragment.Services

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.fragment.Services.response.ServicesItem
import com.bumptech.glide.Glide


class ServiceAdapter (ctx: Context, recomendationList:MutableList<ServicesItem>) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>() {
    private val recommendList: MutableList<ServicesItem>



    var context: Context

    init {
        recommendList=recomendationList
        context=ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val place_list: View = layoutInflater.inflate(R.layout.services_itemview, parent, false)
        return ViewHolder(place_list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = recommendList[position]

        holder.name.text = list.serviceName
        Glide.with(context)
            .load(list.image)
            .into(holder.img)
//        holder.img.setImageResource(list.image)
        holder.img.scaleType = ImageView.ScaleType.FIT_XY

        holder.service_charges.text="$${list.servicePrice}"

        holder.itemView.setOnClickListener {
            var bundle=Bundle()
            bundle.putString("id",list.id.toString())
            bundle.putString("service_name",list.serviceName)
            bundle.putString("service_price",list.servicePrice)
            bundle.putString("description",list.description)
            bundle.putString("image",list.image)
            Navigation.findNavController(it).navigate(R.id.action_servicesFragment_to_serviceDetailFragment,bundle)
        }

    }


    override fun getItemCount(): Int {
        return recommendList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView

        var service_charges: TextView

        var img: ImageView


        init {
            name = itemView.findViewById<TextView>(R.id.catName01)

            service_charges = itemView.findViewById<TextView>(R.id.service_charges)

            img = itemView.findViewById<ImageView>(R.id.catImage01)


        }
    }
}