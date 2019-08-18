package com.stonetree.fallingwords.core.constants.feature.word.model

import com.stonetree.fallingwords.feature.word.model.Guess
import junit.framework.TestCase.assertNull
import org.junit.Test

class GuessTest {

    @Test
    fun test_guessModel_shouldReturnDefaultValues() {
        Guess().apply {
            assertNull(word)
            assertNull(translated)
            assertNull(translations)
        }
    }
}