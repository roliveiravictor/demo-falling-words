package com.stonetree.fallingwords.feature.word.res.repository

import androidx.arch.core.executor.testing.CountingTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.fallingwords.core.constants.Constants.RESULT_KEY
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import com.stonetree.fallingwords.core.constants.TestConstants
import com.stonetree.fallingwords.core.extensions.read
import com.stonetree.fallingwords.feature.word.model.Guess
import junit.framework.TestCase.*
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.verify
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class WordRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = WordRepository.getInstance()

    @Rule
    @JvmField
    val count = CountingTaskExecutorRule()

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

    @Test
    fun test_next_shouldReturnOneLess() {
        val guess = Guess().also { guess ->
            guess.translations = arrayListOf("1", "2", "3", "4", "5")
        }
        repository.getGuess().postValue(guess)

        count.drainTasks(1, TimeUnit.SECONDS)

        repository.getGuess()?.apply {
            repository.next()

            count.drainTasks(1, TimeUnit.SECONDS)

            assertEquals(4, value?.translations?.size)
        }
    }

    @Test
    fun test_nextOverflow_shouldReturnNotEmpty() {
        val guess = Guess().also { guess ->
            guess.translations = arrayListOf("1", "2", "3", "4", "5")
        }
        repository.getGuess().postValue(guess)

        count.drainTasks(1, TimeUnit.SECONDS)

        for(i in 1..10)
            repository.next()

        count.drainTasks(1, TimeUnit.SECONDS)

        repository.getGuess()?.apply {
            assertEquals(1, value?.translations?.size)
        }
    }

    @Test
    fun test_generateBundle_shouldReturnLose() {
        val guess = Guess().also { guess ->
            guess.translations = arrayListOf("1")
        }
        repository.getGuess().postValue(guess)

        count.drainTasks(1, TimeUnit.SECONDS)

        val bundle = repository.generateBundle()
        assertFalse(bundle.getBoolean(RESULT_KEY))
    }

    @Test
    fun test_generateBundle_shouldReturnWin() {
        val guess = Guess().also { guess ->
            guess.translated = "1"
            guess.translations = arrayListOf("1")
        }
        repository.getGuess().postValue(guess)

        count.drainTasks(1, TimeUnit.SECONDS)

        repository.generateBundle()?.
            getBoolean(RESULT_KEY)?.
            apply {
                assertTrue(this)
            }
    }
}