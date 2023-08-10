package com.app.wallmount.controller.fragment.forOurGuest

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
import com.app.wallmount.controller.fragment.forOurGuest.response.OurguestsItem

import com.bumptech.glide.Glide


class ForOurGuestRecyclerAdapter(ctx: Context, recomendationList: List<OurguestsItem>) :
    RecyclerView.Adapter<ForOurGuestRecyclerAdapter.ViewHolder>() {
    private val recommendList: List<OurguestsItem>


    var context: Context

    init {
        recommendList = recomendationList
        context = ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val place_list: View =
            layoutInflater.inflate(R.layout.for_our_guest_recycler_itemview, parent, false)
        return ViewHolder(place_list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = recommendList[position]

        holder.name.text = list.title
        Glide.with(context)
            .load(list.uploadImages)
            .into(holder.img)
        holder.img.scaleType = ImageView.ScaleType.FIT_XY

        holder.detail.text = list.description
        holder.seeMore.setOnClickListener {

            val bundle = Bundle()
            bundle.putString("guest_id", list.id.toString())

            Navigation.findNavController(it).navigate(
                R.id.action_forOurGuestRecyclerFragment_to_forOurGuestDetailFragment,
                bundle
            )

        }
    }


    override fun getItemCount(): Int {
        return recommendList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView

        var detail: TextView
        var seeMore: TextView
        var img: ImageView


        init {
            name = itemView.findViewById<TextView>(R.id.catName01)
            seeMore = itemView.findViewById<TextView>(R.id.see_details)
            detail = itemView.findViewById<TextView>(R.id.detail)
            img = itemView.findViewById<ImageView>(R.id.catImage01)


        }
    }
}