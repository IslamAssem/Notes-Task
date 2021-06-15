package com.ibnsina.notes.features.notes

import androidx.navigation.NavController
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.*
import com.ibnsina.notes.R
import com.ibnsina.notes.features.login.LoginFragment
import com.ibnsina.notes.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class NotesFragmentTest {

    private lateinit var navController: NavController
    @Before
    fun setup() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        runOnUiThread{navController.setGraph(R.navigation.nav_graph_notes_start)}
        launchFragmentInHiltContainer<NotesFragment>(navController = navController)
    }

    @Test
    fun checkStartFragment(){
        Thread.sleep(1000)//wait for recycler items to be populated
        assertEquals(navController.currentDestination?.id,R.id.nav_notes)
    }
    //I run this test after adding at least 5 notes
    @Test
    fun testViewsVisibility(){
        Thread.sleep(1000)//wait for recycler items to be populated
        onView(withId(R.id.empty)).check(matches(not(isDisplayed())))
    }
    @Test
    fun testNavigationToNoteDetails() {
        Thread.sleep(1000)//wait for recycler items to be populated
        onView(withId(R.id.recycler_view))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    Random.nextInt(5), click()
                )
            )
        assertEquals(navController.currentDestination?.id,R.id.nav_add_note)
    }
}