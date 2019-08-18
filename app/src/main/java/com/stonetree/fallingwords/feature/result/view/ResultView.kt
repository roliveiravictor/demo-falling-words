package com.stonetree.fallingwords.feature.result.view


import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.core.constants.Constants.LOSE_FILE
import com.stonetree.fallingwords.core.constants.Constants.RESULT_TIMEOUT
import com.stonetree.fallingwords.core.constants.Constants.WIN_FILE
import com.stonetree.fallingwords.databinding.ViewResultBinding

class ResultView : Fragment() {

    private val args: ResultViewArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val data = ViewResultBinding.inflate(inflater, viewGroup, false)
        return data.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindAnimation()
        bindTitle()
        Handler().postDelayed( { navigateToWordView() }, RESULT_TIMEOUT)
    }

    private fun bindAnimation() {
        view?.findViewById<LottieAnimationView>(R.id.result)?.apply {
            setAnimation(fileName())
        }
    }

    private fun bindTitle() {
        activity?.title = title()
    }

    private fun fileName(): String {
        return if (args.result) WIN_FILE else LOSE_FILE
    }

    private fun title(): String {
        return if (args.result)
            getString(R.string.word_win)
        else
            getString(R.string.word_lose)
    }

    private fun navigateToWordView() {
        findNavController().navigate(R.id.action_result_to_start_view)
    }
}
