package `in`.pawan.dogsapp

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import `in`.pawan.dogsapp.MainActivity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    private var isPaused = false

    @Before
    fun setup() {
        Intents.init()
    }

    @Test
    fun testDeeplinkSuccessCase() {
        val deepLinkUri = Uri.parse("https://pawanpal1.app.link/dz0aOukDlyb")
        val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
        activityRule.scenario.onActivity { activity ->
            activity.startActivity(intent)
        }

        activityRule.scenario.onActivity { activity ->
            activity.lifecycle.addObserver(object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if (event == Lifecycle.Event.ON_PAUSE) {
                        isPaused = true
                    }
                }
            })
        }

        onView(withText("Branch Deeplinking Routing")).check(ViewAssertions.matches(isDisplayed()))

        try {
            // if product shown then click yes
            onView(withText("Yes")).perform(click())
            intended(hasAction(Intent.ACTION_VIEW))
        } catch (e: NoMatchingViewException) {
            fail("Expected navigation did not occur after clicking 'Yes'")
        }


        assertTrue(isPaused)

    }

    @Test
    fun testDeeplinkFailCase() {
        val deepLinkUri = Uri.parse("https://pawanpal1.app.link/dz0aOukDlyb")
        val intent = Intent(Intent.ACTION_VIEW, deepLinkUri)
        activityRule.scenario.onActivity { activity ->
            activity.startActivity(intent)
        }
        onView(withText("Branch Deeplinking Routing")).check(ViewAssertions.matches(isDisplayed()))

        try {
            onView(withText("No")).perform(click())
            intended(hasAction(Intent.ACTION_VIEW))
        } catch (e: NoMatchingViewException) {
            fail("Expected navigation did not occur after clicking 'Yes'")
        }
    }

    @After
    fun tearDown() {
        Intents.release()
    }
}