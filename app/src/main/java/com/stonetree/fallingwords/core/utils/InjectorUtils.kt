package com.stonetree.fallingwords.core.utils

import com.stonetree.fallingwords.feature.word.res.factory.WordViewModelFactory

object InjectorUtils {

    fun provideLatestViewModelFactory(): WordViewModelFactory {
        return WordViewModelFactory()
    }
}