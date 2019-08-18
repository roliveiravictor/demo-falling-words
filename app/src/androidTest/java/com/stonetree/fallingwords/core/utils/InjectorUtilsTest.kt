package com.stonetree.fallingwords.core.utils

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.fallingwords.feature.word.res.factory.WordViewModelFactory
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class InjectorUtilsTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_injectorUtils_shouldReturnDefaultValues() {
        val factory = InjectorUtils.provideWordViewModelFactory(context)
        assertThat(factory,`is`(any(WordViewModelFactory::class.java)))
    }
}