package com.example.dogglers

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.BoundedMatcher
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.dogglers.BaseTest.DrawableMatcher.withDrawable
import com.example.dogglers.data.DataSource
import org.hamcrest.CoreMatchers
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.lang.IllegalStateException

open class BaseTest {

    val lastPosition = DataSource.dogs.size - 1

    private fun hasListItemContent(name: String, age: String, hobbies: String, imageResource: Int) {
        onView(withText(name))
            .check(matches(isDisplayed()))
        onView(withText(age))
            .check(matches(isDisplayed()))
        onView(withText(hobbies))
            .check(matches(isDisplayed()))
        onView(withDrawable(imageResource))
            .check(matches(isDisplayed()))
    }

    fun checkFirstPosition() {
        hasListItemContent("Tzeitel", "7", "sunbathing",
            R.drawable.tzeitel)
    }

    object DrawableMatcher {

       
        fun hasItemCount(count: Int): ViewAssertion {
            return RecyclerViewAssertion(count)
        }

        fun withDrawable(@DrawableRes resourceId: Int): Matcher<View> {
            return object : BoundedMatcher<View, ImageView>(ImageView::class.java) {
                override fun describeTo(description: Description?) {
                    description!!.appendText("has drawable resource $resourceId")
                }

                override fun matchesSafely(imageView: ImageView): Boolean {
                    return isSameBitmap(imageView, imageView.drawable, resourceId)
                }
            }
        }

        private fun isSameBitmap(item: View, drawable: Drawable?, expectedResId: Int): Boolean {
            val image = item as ImageView
            if (expectedResId < 0) {
                return image.drawable == null
            }
            val expectedDrawable: Drawable? = ContextCompat.getDrawable(item.context, expectedResId)
            if (drawable == null || expectedDrawable == null) {
                return false
            }

            if (drawable is BitmapDrawable && expectedDrawable is BitmapDrawable) {
                val found = drawable.bitmap
                val expected = expectedDrawable.bitmap
                return found.sameAs(expected)
            }

            drawable.setTint(android.R.color.black)
            expectedDrawable.setTint(android.R.color.black)
            val bitmap = getBitmap(drawable)
            val expectedBitmap = getBitmap(expectedDrawable)
            return bitmap.sameAs(expectedBitmap)
        }

        private fun getBitmap(drawable: Drawable): Bitmap {
            val bitmap = Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        }

        private class RecyclerViewAssertion(private val count: Int) : ViewAssertion {
            override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The view is not a RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter assigned to RecyclerView")
                }

                ViewMatchers.assertThat(
                    "RecyclerView item count",
                    view.adapter?.itemCount,
                    CoreMatchers.equalTo(count)
                )
            }
        }
    }
}
