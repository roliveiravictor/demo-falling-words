package com.stonetree.fallingwords.feature.word.res.repository

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import com.stonetree.fallingwords.core.extensions.read
import com.google.gson.Gson
import com.stonetree.fallingwords.core.constants.Constants.RESULT_KEY
import com.stonetree.fallingwords.core.extensions.buildMain
import com.stonetree.fallingwords.core.extensions.withRandomTranslations
import com.stonetree.fallingwords.core.extensions.withInjectedAnswer
import com.stonetree.fallingwords.feature.word.model.Guess
import com.stonetree.fallingwords.feature.word.model.WordModel

class WordRepository {

    private val words = MutableLiveData<Guess>()

    fun getGuess(): MutableLiveData<Guess> {
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
            words.postValue(createGuess(model))
        }
    }

    private fun createGuess(model: List<WordModel>): Guess = Guess()
        .buildMain(model)
        .withRandomTranslations(model)
        .withInjectedAnswer()

    fun next(): Boolean {
        words.apply {
            value?.translations?.let { translations ->
                val hasNextWord = translations.size > 1
                if (hasNextWord) {
                    translations.removeAt(0)
                    postValue(value)
                    return true
                }
            }
        }
        return false
    }

    fun generateBundle(): Bundle {
        val bundle = Bundle()
        words.value?.apply {
            translations?.first().let { toMatchTranslated ->
                if (translated.equals(toMatchTranslated))
                    bundle.putBoolean(RESULT_KEY, true)
                else
                    bundle.putBoolean(RESULT_KEY, false)
            }
        }
        return bundle
    }
}
