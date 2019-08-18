package com.stonetree.fallingwords.feature.word.view

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.core.constants.Constants.WORD_TIMEOUT
import com.stonetree.fallingwords.core.utils.InjectorUtils
import com.stonetree.fallingwords.databinding.ViewWordBinding
import com.stonetree.fallingwords.feature.word.viewmodel.WordViewModel
import java.lang.reflect.Modifier
import java.util.*
import kotlin.concurrent.timerTask

class WordView : Fragment() {

    private var timer: Timer? = null

    @VisibleForTesting(otherwise = Modifier.PRIVATE)
    val vm: WordViewModel by viewModels {
        InjectorUtils.provideWordViewModelFactory(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val data = ViewWordBinding.inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)

        return data.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timer = Timer().also { timer ->
            timer.schedule(gameOver(), WORD_TIMEOUT)
        }
    }

    private fun gameOver() = timerTask {
        view?.apply { navigateToResultView(this) }
    }

    override fun onStop() {
        super.onStop()
        timer?.cancel()
    }

    private fun bindXml(
        data: ViewWordBinding
    ) {
        data.view = this@WordView
    }

    private fun bindObservers(
        data: ViewWordBinding
    ){
        vm.guess.observe(viewLifecycleOwner) { guess ->
            updateTranslations(data, guess.translations)
            updateTitle(guess.word)
        }
    }

    private fun updateTranslations(
        data: ViewWordBinding,
        translations: ArrayList<String>?)
    {
        data.translated.text = translations?.first()
    }

    private fun updateTitle(title: String?) {
        activity?.title = getString(R.string.word_translation_title, title)
    }

    fun navigateToResultView(view: View) {
        findNavController().navigate(R.id.action_word_to_result_view, vm.generateBundle())
    }

    fun popNextWord(view: View) {
        vm.next().let { hasNext ->
            if(!hasNext)
                navigateToResultView(view)
        }
    }
}