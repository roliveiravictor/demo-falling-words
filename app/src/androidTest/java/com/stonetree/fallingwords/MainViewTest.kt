package com.stonetree.fallingwords

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.fallingwords.core.extensions.execute
import com.stonetree.fallingwords.core.extensions.launchFragmentScenario
import com.stonetree.fallingwords.feature.word.view.WordView
import junit.framework.TestCase.*
import kotlinx.android.synthetic.main.nav_fragment.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    private var fragment: WordView? = null

    @Before
    fun setup() {
        jumpToWordViewFragment()
    }

    private fun jumpToWordViewFragment() {
        rule.activity
            .findNavController(R.id.nav_fragment)
            .launchFragmentScenario(null, WordView())
            .execute { fragment ->
                this@MainViewTest.fragment = fragment
            }
    }

    @Test
    fun test_findNavFragment_shouldReturnNotNull() {
        assertNotNull(rule.activity.nav_fragment)
    }

    @Test
    fun test_navigationWordView_shouldNavigateToWordFragment() {
        onView(withId(R.id.word))
            .check(matches(isDisplayed()))
    }
}