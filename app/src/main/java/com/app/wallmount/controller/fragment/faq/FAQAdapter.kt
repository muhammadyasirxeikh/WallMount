package com.app.wallmount.controller.fragment.faq

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.wallmount.R
import com.app.wallmount.controller.fragment.faq.response.FaqsItem

class FAQAdapter(private val faqs: List<FaqsItem>) : RecyclerView.Adapter<FAQAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.faq_itemview, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val faqItem = faqs[position]

        holder.questionTextView.text = faqItem.question
        holder.answerTextView.text = faqItem.answer
    }

    override fun getItemCount(): Int {
        return faqs.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val questionTextView: TextView = itemView.findViewById(R.id.question)
        val answerTextView: TextView = itemView.findViewById(R.id.answer)
    }
}
