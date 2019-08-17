package com.stonetree.fallingwords.feature.word.viewmodel

import androidx.lifecycle.ViewModel
import com.stonetree.fallingwords.feature.word.res.repository.WordRepository

class WordViewModel: ViewModel() {

    private val repository = WordRepository.getInstance()
}