package com.stonetree.fallingwords.core.constants.core.constants

import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun test_wordsPath_shouldReturnFile() {
        assertEquals("words.json", WORDS_FILE)
    }
}
