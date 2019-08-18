package com.stonetree.fallingwords.core.constants.core.extensions

import com.stonetree.fallingwords.core.extensions.buildMain
import com.stonetree.fallingwords.core.extensions.withInjectedAnswer
import com.stonetree.fallingwords.core.extensions.withRandomTranslations
import com.stonetree.fallingwords.feature.word.model.Guess
import com.stonetree.fallingwords.feature.word.model.WordModel
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertEquals
import org.junit.Test

class GuessExtensionTest {

    @Test(expected = NoSuchElementException::class)
    fun test_buildMain_shouldReturnDefault() {
        Guess().buildMain(arrayListOf()).apply {
            assertNull(word)
            assertNull(translated)
            assertNull(translations)
        }
    }

    @Test
    fun test_buildMainWithList_shouldReturnDefault() {
        val model = WordModel()
        model.english = "english"
        model.spanish = "spanish"

        val list = arrayListOf<WordModel>()
        list.add(model)

        Guess().buildMain(list).apply {
            assertEquals("english", word)
            assertEquals("spanish", translated)
            assertNotNull(translations)
            assertEquals(0, translations?.size)
        }
    }

    @Test
    fun test_withTranslations_shouldReturnDefault() {
        val model = WordModel()
        model.english = "english"
        model.spanish = "spanish"

        val list = arrayListOf<WordModel>()
        list.add(model)

        Guess().buildMain(list)
            .withRandomTranslations(list)
            .apply {
                assertEquals("english", word)
                assertEquals("spanish", translated)
                assertEquals(4, translations?.size)
            }
    }

    @Test
    fun test_withInjectedAnswer_shouldReturnDefault() {
        val model = WordModel()
        model.english = "english"
        model.spanish = "spanish"

        val list = arrayListOf<WordModel>()
        list.add(model)

        Guess().buildMain(list)
            .withRandomTranslations(list)
            .withInjectedAnswer()
            .apply {
                assertEquals("english", word)
                assertEquals("spanish", translated)
                assertEquals(5, translations?.size)
            }
    }
}