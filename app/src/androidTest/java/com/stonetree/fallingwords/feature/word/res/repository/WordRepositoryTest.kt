package com.stonetree.fallingwords.feature.word.res.repository

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import com.stonetree.fallingwords.core.constants.TestConstants
import com.stonetree.fallingwords.core.extensions.read
import junit.framework.TestCase.assertEquals
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WordRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = WordRepository.getInstance()

    @Test
    fun test_mutableLiveData_shouldReturnObject() {
        repository.apply {
            assertThat(getGuess(),`is`(any(MutableLiveData::class.java)))
        }
    }

    @Test
    fun test_get_shouldReturnWords() {
        assertNotEquals("", repository.get(context))
    }

    @Test
    fun test_createGuess_shouldReturnDefaultValues() {
        repository.get(context)
        repository.getGuess().value?.apply {
            assertNotNull(word)
            assertNotNull(translated)
            assertNotNull(translations)
            assertEquals(5, translations?.size)
        }
    }
}