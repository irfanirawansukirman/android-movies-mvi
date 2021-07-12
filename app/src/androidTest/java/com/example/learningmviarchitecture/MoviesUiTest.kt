package com.example.learningmviarchitecture

import android.os.SystemClock
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.learningmviarchitecture.feature.movies.MoviesActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class MoviesUiTest : BaseUiTest() {

    /**
     * Use [ActivityScenarioRule] to create and launch the activity under test before each test,
     * and close it after each test. This is a replacement for
     * [androidx.test.rule.ActivityTestRule].
     */
    @get:Rule
    var activityScenarioRule = activityScenarioRule<MoviesActivity>()

    @Test
    fun showMoviesPopularIsSuccess() {
        mockSuccess(200, "responses/movies_popular_success.json")

//        onView(withId(R.id.btn_submit)).apply {
//            check(matches(isDisplayed()))
//            check(matches(withText("Get Movies")))
//            perform(click())
//        }

        SystemClock.sleep(2_000)

        onView(withId(R.id.tv_title)).check(matches(withText("Infinite")))
    }
}
