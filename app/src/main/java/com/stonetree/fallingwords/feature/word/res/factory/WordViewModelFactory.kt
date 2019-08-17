package com.stonetree.fallingwords.feature.word.res.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stonetree.fallingwords.feature.word.viewmodel.WordViewModel

class WordViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel() as T
    }
}
