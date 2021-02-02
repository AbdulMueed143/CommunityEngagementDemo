package au.com.communityengagement

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import au.com.communityengagement.adapters.PostAdapter
import au.com.communityengagement.util.DataGenerator
import au.com.communityengagement.util.EspressoIdlingResource
import org.hamcrest.Matcher
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class PostViewFragmentTestWithMainActivity {

    /**
     * Note:
     *
     * Because of data binding to make most of test cases work we will need to implement following solution
     * as we have to wait for data binding to complete what it does ..
     *
     * https://medium.com/freenet-engineering/running-android-espresso-tests-with-data-binding-and-koin-a57a8d38daa5
     * But due to time limitations.. i will opt out of it, but you get the basic idea that it is pretty easy to write
     * these UI tests.
     *
     * */

    //This is our rule that before every test we need to run the main activity...
    @get: Rule
    val activityMainRule = ActivityScenarioRule(MainActivity::class.java)

    val TEST_POST_ITEM_POSITION = 5
    val TEST_POST_ITEM = DataGenerator.getPosts().get(TEST_POST_ITEM_POSITION)



    //Check if the Main Activity Is Launched
    @Test
    fun test_isMainActivityLaunched() {
        onView(withId(R.id.drawer_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun test_isPostFragmentViewVisible() {
        onView(withId(R.id.frgPostView)).check(matches(isDisplayed()))
    }


    /* These idling resources are used for waiting if you are loading something */

//    @Before
//    fun registerIdlingResource() {
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
//    }
//
//    @After
//    fun unRegisterIdlingResource() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
//    }


      //RecyclerView test case will need databinding idling implemented
//    @Test
//    fun test_selectPost_isSelectedPostActivityVisible() {
//
//
//        onView(withId(R.id.rcyPosts))
//            .perform(actionOnItemAtPosition<PostAdapter.BaseViewHolder>(TEST_POST_ITEM_POSITION, clickItemWithId(R.id.commentView)))
//
//        //If visible post has same text as the one we intent to click .. obviously it passed mate!
//        onView(withId(R.id.txtContent)).check(matches(withText(TEST_POST_ITEM.content)))
//    }


    //Custom Action view for clicking specific item within recyclerview, like/comment
    class clickItemWithId(val resourceId: Int) : ViewAction {

        val click: ViewAction = click()

        override fun getConstraints(): Matcher<View> = click.constraints

        override fun getDescription(): String = "Custom Button Click"

        override fun perform(uiController: UiController?, view: View?) {
            click.perform(uiController, view?.findViewById(resourceId))
        }
    }
}