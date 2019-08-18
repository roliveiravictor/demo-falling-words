package com.stonetree.fallingwords.core.constants.core.extensions

import com.stonetree.fallingwords.core.extensions.randomize
import junit.framework.TestCase.assertTrue
import org.junit.Test

class RandomExtensionsTest {

    // TODO - Improve logic to explore all random possibilities
    @Test
    fun test_randomize_shouldReturnExpectedRange() {
        val random = 5.randomize()
        assertTrue(random in 0..4)
    }
}