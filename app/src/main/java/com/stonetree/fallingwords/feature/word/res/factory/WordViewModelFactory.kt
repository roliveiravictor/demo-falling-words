package com.stonetree.fallingwords.feature.word.res.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.stonetree.fallingwords.feature.word.viewmodel.WordViewModel

class WordViewModelFactory(private val context: Context) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WordViewModel(context) as T
    }
}
