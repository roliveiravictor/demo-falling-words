package com.stonetree.fallingwords.core.constants.feature.word.model

import com.stonetree.fallingwords.feature.word.model.WordModel
import junit.framework.TestCase.*
import org.junit.Test

class WordModelTest {

    @Test
    fun test_wordModel_shouldReturnDefaultValues() {
        WordModel().apply {
            assertNull(english)
            assertNull(spanish)
        }
    }
}