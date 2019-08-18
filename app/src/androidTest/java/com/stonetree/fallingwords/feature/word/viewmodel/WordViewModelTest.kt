package com.stonetree.fallingwords.feature.word.viewmodel

import androidx.lifecycle.LiveData
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.any
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.fallingwords.core.extensions.lambdaMock
import com.stonetree.fallingwords.core.extensions.observeLiveData
import com.stonetree.fallingwords.feature.word.model.WordModel
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(AndroidJUnit4::class)
class WordViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var vm: WordViewModel

    @Before
    fun setup(){
        vm = WordViewModel(context)
    }

    @Test
    fun test_wordViewModel_shouldReturnDefaultValues() {
        vm.apply {
            assertThat(guess,`is`(any(LiveData::class.java)))
        }
    }

    @Test
    fun test_words_shouldReturnChangeLivedData() {
        val observer = lambdaMock<(WordModel) -> Unit>()
        val mutableData = vm.observeLiveData("guess", observer)

        val model = WordModel()
        mutableData.postValue(model)
        verify(observer).invoke(model)
    }
}