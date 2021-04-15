package com.mars.ai_lab_7.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.mars.ai_lab_7.R
import com.mars.ai_lab_7.data.AppSettings
import com.mars.ai_lab_7.databinding.FragmentAnalyzeTypeBinding

class AnalyzeTypeFragment : Fragment(R.layout.fragment_analyze_type) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        FragmentAnalyzeTypeBinding.bind(view).run {
            maxMinButton.setOnClickListener {
                AppSettings.useMaxProd = false
                openResults()
            }
            maxProdButton.setOnClickListener {
                AppSettings.useMaxProd = true
                openResults()
            }
        }
    }

    private fun openResults() {
        Navigation.findNavController(view ?: return).navigate(R.id.navigate_to_results)
    }
}