package com.stonetree.fallingwords.core.extensions

import androidx.test.platform.app.InstrumentationRegistry
import com.stonetree.fallingwords.core.constants.TestConstants.TEST_FILE
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryExtensionsTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun test_read_shouldReturnFile() {
        assertEquals("""{"text_eng": "primary school"}""", TEST_FILE.read(context))
    }
}