package com.stonetree.fallingwords.core.utils

import com.stonetree.fallingwords.feature.word.res.factory.WordViewModelFactory

object InjectorUtils {

    fun provideWordViewModelFactory(): WordViewModelFactory {
        return WordViewModelFactory()
    }
}