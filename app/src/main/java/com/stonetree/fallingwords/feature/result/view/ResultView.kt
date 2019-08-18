package com.stonetree.fallingwords.feature.result.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.airbnb.lottie.LottieAnimationView
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.core.constants.Constants
import com.stonetree.fallingwords.core.constants.Constants.LOSE_FILE
import com.stonetree.fallingwords.core.constants.Constants.WIN_FILE
import com.stonetree.fallingwords.databinding.ViewResultBinding
import java.util.*
import kotlin.concurrent.timerTask

class ResultView : Fragment() {

    private var timer: Timer? = null

    private val args: ResultViewArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val data = ViewResultBinding.inflate(inflater, viewGroup, false)

        bindTitle()

        return data.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindAnimation()

        timer = Timer().also { timer ->
            timer.schedule(home(), Constants.RESULT_TIMEOUT)
        }
    }

    private fun home() = timerTask {
        view?.apply { navigateToStartView() }
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
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

    private fun navigateToStartView() {
        findNavController().navigate(R.id.action_result_to_start_view)
    }
}
