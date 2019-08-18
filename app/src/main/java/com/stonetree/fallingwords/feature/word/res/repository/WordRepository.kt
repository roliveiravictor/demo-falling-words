package com.stonetree.fallingwords.feature.word.res.repository

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.MutableLiveData
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import com.stonetree.fallingwords.core.extensions.read
import com.google.gson.Gson
import com.stonetree.fallingwords.core.constants.Constants.RESULT_KEY
import com.stonetree.fallingwords.core.extensions.randomize
import com.stonetree.fallingwords.feature.word.model.Guess
import com.stonetree.fallingwords.feature.word.model.WordModel
import java.lang.reflect.Modifier.PRIVATE

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

    private fun createGuess(model: List<WordModel>): Guess {
        return Guess().also { guess ->
            val randomPos = model.size.randomize()
            guess.word = model[randomPos].english
            guess.translated = model[randomPos].spanish
            guess.translations = arrayListOf()

            guess.translated?.apply {
                guess.translations?.add(this)
            }

            for(i in 0..3) {
                val randomPos = model.size.randomize()
                guess.translations?.let { translations ->
                    model[randomPos].spanish?.apply {
                        translations.add(this)
                    }
                }
            }
        }
    }

    fun next() {
        words.apply {
            value?.translations?.let { translations ->
                if(translations.size > 1) {
                    translations.removeAt(0)
                    postValue(value)
                }
            }
        }
    }

    fun generateBundle(): Bundle {
        val bundle = Bundle()
        words.value?.apply {
            translations?.first().let { toMatchTranslated ->
                if(translated.equals(toMatchTranslated))
                    bundle.putBoolean(RESULT_KEY, true)
                else
                    bundle.putBoolean(RESULT_KEY, false)
            }
        }
        return bundle
    }
}