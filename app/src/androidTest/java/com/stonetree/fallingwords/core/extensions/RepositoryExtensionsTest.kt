package com.stonetree.fallingwords.core.extensions

import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.fallingwords.core.constants.Constants.LOSE_FILE
import com.stonetree.fallingwords.core.constants.Constants.WIN_FILE
import com.stonetree.fallingwords.core.constants.Constants.WORDS_FILE
import com.stonetree.fallingwords.core.constants.TestConstants.TEST_FILE
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryExtensionsTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_read_shouldReturnFile() {
        assertEquals("""{"text_eng": "primary school"}""", TEST_FILE.read(context))
    }

    @Test
    fun test_word_shouldReturnFile() {
        assertNotNull(WORDS_FILE.read(context))
    }

    @Test
    fun test_win_shouldReturnFile() {
        assertNotNull(WIN_FILE.read(context))
    }

    @Test
    fun test_lose_shouldReturnFile() {
        assertNotNull(LOSE_FILE.read(context))
    }
}