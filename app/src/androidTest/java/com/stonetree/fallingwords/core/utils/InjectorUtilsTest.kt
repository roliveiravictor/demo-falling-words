package com.stonetree.fallingwords.core.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.stonetree.fallingwords.feature.word.res.factory.WordViewModelFactory
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InjectorUtilsTest {

    @Test
    fun test_injectorUtils_shouldReturnDefaultValues() {
        val factory = InjectorUtils.provideWordViewModelFactory()
        assertThat(factory,`is`(any(WordViewModelFactory::class.java)))
    }
}