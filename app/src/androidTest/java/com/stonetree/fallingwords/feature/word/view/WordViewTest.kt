package com.stonetree.fallingwords.feature.word.view

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.PerformException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.stonetree.fallingwords.MainView
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.core.constants.TestConstants.PACKAGE
import com.stonetree.fallingwords.core.extensions.execute
import com.stonetree.fallingwords.core.extensions.launchFragmentScenario
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.Before
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordViewTest {

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
                this@WordViewTest.fragment = fragment
            }
    }

    @Test
    fun test_lazyVm_shouldReturnNotNull() {
        assertNotNull(fragment?.vm)
    }

    @Test
    fun test_bindObservers_shouldReturnNothingNull() {
        fragment?.vm?.apply {
            assertTrue(guess.hasObservers())
        }
    }

    @Test
    fun test_useAppContext_shouldReturnPackageName() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals(PACKAGE, context.packageName)
    }

    @Test
    fun test_rootVisibility_shouldReturnVisible() {
        onView(withId(R.id.root_word_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_title_shouldReturnAppName() {
        fragment?.activity?.title?.apply {
            assertTrue(contains("Translate"))
        }
    }

    @Test
    fun test_wordText_shouldReturnFilled() {
        onView(withId(R.id.translated))
            .check(matches(not(withText(""))))
    }

    // TODO - Fix navigation test
    @Test(expected = PerformException::class)
    fun test_navigateToResult_shouldReturnResultView() {
        onView(withId(R.id.right))
            .perform(click())

        val id = rule.activity
            .findNavController(R.id.nav_fragment)
            .currentDestination?.id

        assertEquals(R.id.result_view, id)
    }

    @Test
    fun test_wrong_shouldReturn() {
        onView(withId(R.id.wrong))
            .perform(click())

        fragment?.vm?.guess?.value?.translations?.apply {
            assertEquals(4, size)
        }
    }
}