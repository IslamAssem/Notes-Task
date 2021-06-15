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
class LoginFragmentTest {

    private lateinit var navController: NavController
    @Before
    fun setup() {
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        runOnUiThread{navController.setGraph(R.navigation.nav_graph)}
        launchFragmentInHiltContainer<LoginFragment>(navController = navController)
    }

    @Test
    fun checkStartFragment(){
        assertEquals(navController.currentDestination?.id,R.id.nav_login)
    }
    @Test
    fun doLogin(){
        onView(withId(R.id.login)).check(matches(isDisplayed()))
        Thread.sleep(500)
        onView(withId(R.id.login))
            .perform(click())
        Thread.sleep(1000)
        assertEquals(navController.currentDestination?.id,R.id.nav_notes)
    }
}