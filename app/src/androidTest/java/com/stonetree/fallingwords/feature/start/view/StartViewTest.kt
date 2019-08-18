package com.stonetree.fallingwords.feature.start.view

import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.fallingwords.MainView
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.feature.result.view.ResultView
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StartViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    private var fragment: ResultView? = null

    @Test
    fun test_rootVisibility_shouldReturnVisible() {
        onView(withId(R.id.root_start_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_title_shouldReturnNothing() {
        assertNull(fragment?.activity?.title)
    }

    @Test
    fun test_navigateToWord_shouldReturnWordView() {
        onView(withId(R.id.new_game))
            .perform(click())

        val id = rule.activity
            .findNavController(R.id.nav_fragment)
            .currentDestination?.id

        assertEquals(R.id.word_view, id)
    }
}