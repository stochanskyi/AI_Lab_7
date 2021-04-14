package com.mars.ai_lab_7.presentation.characteristics.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.presentation.characteristics.models.CharacteristicViewData

class CharacteristicsAdapter(
    private val onValueChanged: (id: Int, value: String) -> Unit
) : RecyclerView.Adapter<CharacteristicViewHolder>() {

    private val items: MutableList<CharacteristicViewData> = mutableListOf()

    fun setItems(items: List<CharacteristicViewData>) {
        this.items.apply {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacteristicViewHolder {
        return CharacteristicViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CharacteristicViewHolder, position: Int) {
        holder.bind(items[position], onValueChanged)
    }

    override fun getItemCount(): Int = items.size

    override fun onViewRecycled(holder: CharacteristicViewHolder) {
        holder.recycle()
    }
}