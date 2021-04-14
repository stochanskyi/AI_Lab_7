package com.mars.ai_lab_7.presentation.results.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.databinding.ViewHolderResultItemBinding
import com.mars.ai_lab_7.presentation.results.models.ResultItemViewData

class ResultItemViewHolder private constructor(
    private val binding: ViewHolderResultItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup) = ResultItemViewHolder(
            ViewHolderResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun bind(model: ResultItemViewData) {
        binding.run {
            nameTextView.text = model.name
            valueTextView.text = model.value
        }
    }

}