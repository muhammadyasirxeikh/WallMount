package com.app.wallmount.controller.adapter.forOurGuestMain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.activity.MainActivity
import com.app.wallmount.controller.fragment.home.response.OurguestsItem
import com.bumptech.glide.Glide

class ForGuestAdapter(ctx: Context, private val ourguestlist:List<OurguestsItem>, private val mainActivity: MainActivity) : RecyclerView.Adapter<ForGuestAdapter.ViewHolder>() {
    private val list: List<OurguestsItem>



    var context: Context

   init {
       list=ourguestlist

       context=ctx
   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val car_list: View = layoutInflater.inflate(R.layout.for_our_guest_rv_item, parent, false)
        return ViewHolder(car_list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list_item = list[position]

        holder.name.setText(list_item.title)
        Glide.with(mainActivity)
            .load(list_item.uploadImages)
            .into(holder.img)
//        holder.img.setImageResource(img)
        holder.img.setScaleType(ImageView.ScaleType.FIT_XY)

        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_forOurGuestRecyclerFragment)
        }
    }


    override fun getItemCount(): Int {
        return list!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var img: ImageView


        init {
            name = itemView.findViewById<TextView>(R.id.catName01)
            img = itemView.findViewById<ImageView>(R.id.catImage01)


        }
    }
}


