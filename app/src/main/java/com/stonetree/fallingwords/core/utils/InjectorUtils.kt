package com.stonetree.fallingwords.core.utils

import android.content.Context
import com.stonetree.fallingwords.feature.word.res.factory.WordViewModelFactory

object InjectorUtils {

    fun provideWordViewModelFactory(context: Context): WordViewModelFactory {
        return WordViewModelFactory(context)
    }
}
