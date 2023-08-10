package com.app.wallmount.controller.fragment.feedback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R

import com.app.wallmount.controller.fragment.faq.response.FaqsItem
import com.app.wallmount.controller.fragment.feedback.response.ReviewsItem


class ReviewAdapter(private val faqs: List<ReviewsItem>) : RecyclerView.Adapter<ReviewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.review_recycler_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faqItem = faqs[position]

        holder.questionTextView.text = faqItem.question

    }

    override fun getItemCount(): Int {
        return faqs.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.question)

    }
}

