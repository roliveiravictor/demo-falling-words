package com.stonetree.fallingwords.core.extensions

import com.stonetree.fallingwords.core.constants.Constants.ANSWER_MAX_RANGE
import com.stonetree.fallingwords.core.constants.Constants.TRANSLATIONS_MAX_RANGE
import com.stonetree.fallingwords.feature.word.model.Guess
import com.stonetree.fallingwords.feature.word.model.WordModel

fun Guess.buildMain(model: List<WordModel>): Guess {
    val randomPos = model.size.randomize()
    word = model[randomPos].english
    translated = model[randomPos].spanish
    translations = arrayListOf()
    return this
}

fun Guess.withRandomTranslations(model: List<WordModel>): Guess {
    for(i in 0..TRANSLATIONS_MAX_RANGE) {
        val randomPos = model.size.randomize()
        translations?.let { translations ->
            model[randomPos].spanish?.apply {
                translations.add(this)
            }
        }
    }
    return this
}

fun Guess.withInjectedAnswer(): Guess {
    val randomPos = ANSWER_MAX_RANGE.randomize()
    translated?.apply {
        translations?.add(randomPos, this)
    }
    return this
}
