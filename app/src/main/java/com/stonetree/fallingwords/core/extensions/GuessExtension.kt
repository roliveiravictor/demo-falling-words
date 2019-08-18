package com.stonetree.fallingwords.core.extensions

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
    for(i in 0..3) {
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
    val randomPos = 4.randomize()
    translated?.apply {
        translations?.add(randomPos, this)
    }
    return this
}