package com.app.wallmount.controller.fragment.recomendationFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.fragment.recomendationFragment.response.Recomendationresponse


class ResturanrRecomendationAdapter (ctx: Context,recomendationList:MutableList<Recomendationresponse>) : RecyclerView.Adapter<ResturanrRecomendationAdapter.ViewHolder>() {
    private val recommendList: MutableList<Recomendationresponse>



    var context: Context

    init {
        recommendList=recomendationList
        context=ctx
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val place_list: View = layoutInflater.inflate(R.layout.recomendation_fragment_itemview, parent, false)
        return ViewHolder(place_list)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = recommendList[position]

        holder.name.text = list.name
        holder.img.setImageResource(list.image)
        holder.img.scaleType = ImageView.ScaleType.FIT_XY
        holder.rating.text= list.rating
        holder.distance.text=list.distance
        holder.itemView.setOnClickListener {

            Navigation.findNavController(it).navigate(R.id.action_recomendationsFragment_to_resturantDetailsFragment)

        }
    }


    override fun getItemCount(): Int {
        return recommendList!!.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var rating: TextView
        var reviews: TextView
        var distance: TextView
        var time: TextView
        var img: ImageView


        init {
            name = itemView.findViewById<TextView>(R.id.catName01)
            rating = itemView.findViewById<TextView>(R.id.rating)
            reviews = itemView.findViewById<TextView>(R.id.reviews)
            distance = itemView.findViewById<TextView>(R.id.distance)
            time = itemView.findViewById<TextView>(R.id.time)
            img = itemView.findViewById<ImageView>(R.id.catImage01)


        }
    }
}


