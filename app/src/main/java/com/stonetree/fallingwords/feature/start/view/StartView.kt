package com.stonetree.fallingwords.feature.start.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.databinding.ViewStartBinding

class StartView : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val data = ViewStartBinding.inflate(inflater, viewGroup, false)

        bindXml(data)
        bindTitle()

        return data.root
    }

    private fun bindXml(
        data: ViewStartBinding
    ) {
        data.view = this@StartView
    }

    private fun bindTitle() {
        activity?.title = getString(R.string.app_name)
    }

    fun navigateToWordView(view: View) {
        findNavController().navigate(R.id.action_start_to_word_view)
    }
}
