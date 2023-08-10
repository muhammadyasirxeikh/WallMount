package com.app.wallmount.controller.fragment.localAttractions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.fragment.recomendationFragment.response.Recomendationresponse


class LocalAttractionAdapter (ctx: Context, recomendationList:MutableList<Recomendationresponse>) : RecyclerView.Adapter<LocalAttractionAdapter.ViewHolder>() {
    private val recommendList: MutableList<Recomendationresponse>



    var context: Context

    init {
        recommendList=recomendationList
        context=ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val place_list: View = layoutInflater.inflate(R.layout.explore_itemview, parent, false)
        return ViewHolder(place_list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = recommendList[position]

        holder.name.text = list.name
        holder.img.setImageResource(list.image)
        holder.img.scaleType = ImageView.ScaleType.FIT_XY

        holder.distance.text=list.distance

    }


    override fun getItemCount(): Int {
        return recommendList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView

        var distance: TextView
        var time: TextView
        var img: ImageView


        init {
            name = itemView.findViewById<TextView>(R.id.catName01)

            distance = itemView.findViewById<TextView>(R.id.distance)
            time = itemView.findViewById<TextView>(R.id.time)
            img = itemView.findViewById<ImageView>(R.id.catImage01)


        }
    }
}