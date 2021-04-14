package com.mars.ai_lab_7.presentation.results.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.presentation.results.adapter.viewholder.ResultItemViewHolder
import com.mars.ai_lab_7.presentation.results.models.ResultItemViewData

class ResultsAdapter : RecyclerView.Adapter<ResultItemViewHolder>() {

    private val items: MutableList<ResultItemViewData> = mutableListOf()

    fun setItems(items: List<ResultItemViewData>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ResultItemViewHolder.create(parent)

    override fun onBindViewHolder(holder: ResultItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

}