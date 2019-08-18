package com.stonetree.fallingwords.core.constants.core.constants

import com.stonetree.fallingwords.core.constants.Constants.LOSE_FILE
import com.stonetree.fallingwords.core.constants.Constants.RESULT_TIMEOUT
import com.stonetree.fallingwords.core.constants.Constants.WIN_FILE
import com.stonetree.fallingwords.core.constants.Constants.WORD_TIMEOUT
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ConstantsTest {

    @Test
    fun test_wordsPath_shouldReturnFile() {
        assertEquals("words.json", WORDS_FILE)
        assertEquals("win.json", WIN_FILE)
        assertEquals("lose.json", LOSE_FILE)
        assertEquals(11000L, WORD_TIMEOUT)
        assertEquals(3000L, RESULT_TIMEOUT)
    }
}
