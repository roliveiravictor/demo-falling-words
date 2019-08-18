package com.stonetree.fallingwords.feature.word.res.repository

import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordRepositoryTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private val repository = WordRepository.getInstance()

    @Test
    fun test_mutableLiveData_shouldReturnObject() {
        repository.apply {
            assertThat(getWords(),`is`(any(MutableLiveData::class.java)))
        }
    }

    @Test
    fun test_get_shouldReturnWords() {
        assertNotEquals("", repository.get(context))
    }
}