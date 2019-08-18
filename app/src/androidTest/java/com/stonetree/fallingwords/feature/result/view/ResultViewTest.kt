package com.stonetree.fallingwords.feature.result.view

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.stonetree.fallingwords.MainView
import com.stonetree.fallingwords.R
import com.stonetree.fallingwords.core.constants.Constants.RESULT_KEY
import com.stonetree.fallingwords.core.extensions.execute
import com.stonetree.fallingwords.core.extensions.launchFragmentScenario
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ResultViewTest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainView::class.java)

    private var fragment: ResultView? = null

    @Before
    fun setup() {
        jumpToResultViewFragment()
    }

    private fun jumpToResultViewFragment() {
        val bundle = Bundle()
        bundle.putBoolean(RESULT_KEY, false)

        rule.activity
            .findNavController(R.id.nav_fragment)
            .launchFragmentScenario(bundle, ResultView())
            .execute { fragment ->
                this@ResultViewTest.fragment = fragment
            }
    }

    @Test
    fun test_rootVisibility_shouldReturnVisible() {
        onView(withId(R.id.root_result_view))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_title_shouldReturnLose() {
        val lose = rule.activity.getString(R.string.word_lose)
        assertEquals(lose, fragment?.activity?.title)
    }
}