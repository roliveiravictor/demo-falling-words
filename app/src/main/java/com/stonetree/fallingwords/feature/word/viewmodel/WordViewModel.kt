package com.stonetree.fallingwords.feature.word.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stonetree.fallingwords.feature.word.model.WordModel
import com.stonetree.fallingwords.feature.word.res.repository.WordRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class WordViewModel(private val context: Context): ViewModel() {

    private val repository = WordRepository.getInstance()

    val words: LiveData<List<WordModel>> = repository.getWords()

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {
        viewModelScope.launch {
            repository.get(context)
        }
    }
}