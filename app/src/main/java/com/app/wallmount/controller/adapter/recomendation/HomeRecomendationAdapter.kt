package com.app.wallmount.controller.adapter.recomendation

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R


class HomeRecomendationAdapter (ctx: Context, name: List<String>, rating: List<String>, placeType: List<String>, images: List<Int>) : RecyclerView.Adapter<HomeRecomendationAdapter.ViewHolder>() {
    private val place_name: List<String>
    private val place_rating: List<String>
    private val place_type: List<String>
    private val place_pics: List<Int>


    var context: Context

    init {
        place_name=name
        place_rating=rating
        place_type=placeType
        place_pics=images
        context=ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val place_list: View = layoutInflater.inflate(R.layout.recomendation_rv_itemview, parent, false)
        return ViewHolder(place_list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val img = place_pics[position]
        val currentText = place_name[position]
        val currentRating = place_rating[position]
        val currentPlaceType = place_type[position]
        holder.name.text = currentText
        holder.img.setImageResource(img)
        holder.img.scaleType = ImageView.ScaleType.CENTER_CROP
        holder.rating.text= currentRating
        holder.placeType.text=currentPlaceType

        holder.itemView.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_resturantDetailsFragment)
        }

    }


    override fun getItemCount(): Int {
        return place_name!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var rating: TextView
        var placeType: TextView
        var img: ImageView


        init {
            name = itemView.findViewById<TextView>(R.id.catName01)
            rating = itemView.findViewById<TextView>(R.id.rating)
            placeType = itemView.findViewById<TextView>(R.id.place_type)
            img = itemView.findViewById<ImageView>(R.id.catImage01)


        }
    }
}


