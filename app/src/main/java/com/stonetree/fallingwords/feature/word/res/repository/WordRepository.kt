package com.stonetree.fallingwords.feature.word.res.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import com.stonetree.fallingwords.core.extensions.read
import com.google.gson.Gson
import com.stonetree.fallingwords.feature.word.model.WordModel


class WordRepository {

    private val words = MutableLiveData<List<WordModel>>()

    fun getWords(): MutableLiveData<List<WordModel>> {
        return words
    }

    companion object {
        @Volatile
        private var instance: WordRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            WordRepository().also { repository ->
                instance = repository
            }
        }
    }

    fun get(context: Context) {
        WORDS_FILE.read(context)?.apply {
            val model = Gson().fromJson(this, Array<WordModel>::class.java).toList()
            words.postValue(model)
        }
    }
}