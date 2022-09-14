package com.example.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.lunchtray.ui.order.*
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class NavigationTests : BaseTest() {

    
    @Test
    fun `navigate_to_entree_menu_from_start_order`() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        val startOrderScenario =
            launchFragmentInContainer<StartOrderFragment>(themeResId = R.style.Theme_LunchTray)

        startOrderScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.start_order_btn)).perform(click())

        assertEquals(navController.currentDestination?.id, R.id.entreeMenuFragment)
    }

    @Test
    fun `navigate_to_start_order_from_entree_menu`() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        val entreeMenuScenario =
            launchFragmentInContainer<EntreeMenuFragment>(themeResId = R.style.Theme_LunchTray)

        entreeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            
            navController.setCurrentDestination(destId = R.id.entreeMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.cancel_button)).perform(click())

        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }


    @Test
    fun `navigate_to_side_menu_from_entree_menu`() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        val entreeMenuScenario =
            launchFragmentInContainer<EntreeMenuFragment>(themeResId = R.style.Theme_LunchTray)

        entreeMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            
            navController.setCurrentDestination(destId = R.id.entreeMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        onView(withId(R.id.next_button)).perform(click())

        assertEquals(navController.currentDestination?.id, R.id.sideMenuFragment)
    }

   
    @Test
    fun `navigate_to_start_order_from_side_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val sideMenuScenario =
            launchFragmentInContainer<SideMenuFragment>(themeResId = R.style.Theme_LunchTray)
        sideMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.sideMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    
    @Test
    fun `navigate_to_accompaniment_menu_from_side_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val sideMenuScenario =
            launchFragmentInContainer<SideMenuFragment>(themeResId = R.style.Theme_LunchTray)
        sideMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.sideMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.next_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.accompanimentMenuFragment)
    }

    @Test
    fun `navigate_to_start_order_from_accompaniment_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val accompanimentMenuScenario =
            launchFragmentInContainer<AccompanimentMenuFragment>(
                themeResId = R.style.Theme_LunchTray)
        accompanimentMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.accompanimentMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    
    @Test
    fun `navigate_to_checkout_from_accompaniment_menu`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val accompanimentMenuScenario =
            launchFragmentInContainer<AccompanimentMenuFragment>(
                themeResId = R.style.Theme_LunchTray)
        accompanimentMenuScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.accompanimentMenuFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.next_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.checkoutFragment)
    }

    
    @Test
    fun `navigate_to_start_order_from_checkout`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val checkoutScenario =
            launchFragmentInContainer<CheckoutFragment>(themeResId = R.style.Theme_LunchTray)
        checkoutScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.checkoutFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.cancel_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }

    
    @Test
    fun `navigate_to_start_order_from_checkout_via_submit`() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        val checkoutScenario =
            launchFragmentInContainer<CheckoutFragment>(themeResId = R.style.Theme_LunchTray)
        checkoutScenario.onFragment{ fragment ->
            navController.setGraph(R.navigation.mobile_navigation)
            navController.setCurrentDestination(destId = R.id.checkoutFragment)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(withId(R.id.submit_button)).perform(click())
        assertEquals(navController.currentDestination?.id, R.id.startOrder)
    }
}
