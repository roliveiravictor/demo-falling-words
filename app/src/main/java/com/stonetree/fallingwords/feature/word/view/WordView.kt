package com.stonetree.fallingwords.feature.word.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.stonetree.fallingwords.core.utils.InjectorUtils
import com.stonetree.fallingwords.databinding.ViewWordBinding
import com.stonetree.fallingwords.feature.word.viewmodel.WordViewModel
import java.lang.reflect.Modifier

class WordView : Fragment() {

    @VisibleForTesting(otherwise = Modifier.PRIVATE)
    val vm: WordViewModel by viewModels {
        InjectorUtils.provideWordViewModelFactory()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              viewGroup: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        val data = ViewWordBinding.inflate(inflater, viewGroup, false)

        bindXml(data)
        bindObservers(data)
        bindObservers(data)

        return data.root
    }

    private fun bindXml(
        data: ViewWordBinding
    ) {
        data.view = this@WordView

        // Do Nothing
    }

    private fun bindObservers(
        data: ViewWordBinding
    ){
        // Do Nothing
    }
}