package com.mars.ai_lab_7.presentation.positions.adapter

import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.mars.ai_lab_7.databinding.ViewHolderCharacteristicBinding
import com.mars.ai_lab_7.presentation.positions.models.CharacteristicViewData

class CharacteristicViewHolder private constructor(
    private val binding: ViewHolderCharacteristicBinding
) : RecyclerView.ViewHolder(binding.root) {

    private var watcher: TextWatcher? = null

    companion object {
        fun create(parent: ViewGroup) = CharacteristicViewHolder(
            ViewHolderCharacteristicBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun bind(
        model: CharacteristicViewData,
        onValueChanged: (Int, String) -> Unit
    ) {
        binding.run {
            nameTextView.text = model.name
            valueEditText.apply {
                setText(model.defaultData)
                watcher = doAfterTextChanged { onValueChanged(model.id, text.toString()) }
            }
        }
    }

    fun recycle() {
        binding.valueEditText.removeTextChangedListener(watcher)
        watcher = null
    }

}