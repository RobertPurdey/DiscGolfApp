package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.atPositionOnView;
import static robert.purdey.caddytracker.EsspressoHelper.clickNestedViewWithId;

public class CourseRecordScreen
{
    public static void countHoles(int expectedHoleCount)
    {
        onView( withId(R.id.rcvw_fragment_course_course_holes) )
            .check( new RecyclerViewItemCountAssertion(expectedHoleCount) );
    }

    public static void clickAddCourseHole()
    {
        onView( withId(R.id.bttn_course_record_add_hole) )
            .perform( click() );
    }

    public static void clickRemoveCourseHole()
    {
        onView( withId(R.id.bttn_course_record_remove_hole) )
            .perform( click() );
    }

    public static void matchCourseName(String expectedCourseName)
    {
        onView( withId(R.id.etxt_activity_course_record_course_name) )
            .check( matches( withText(expectedCourseName) ) );
    }

    public static void setCourseName(String courseName)
    {
        onView( withId(R.id.etxt_activity_course_record_course_name) )
            .perform( clearText() )
            .perform( typeText(courseName) );
    }

    public static void clickSaveCourse()
    {
        onView( withId(R.id.bttn_course_record_create_course) )
            .perform( click() );
    }

    public static void decreaseCourseHolePar(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_course_course_holes) )
            .perform(actionOnItemAtPosition(pos, clickNestedViewWithId(R.id.bttn_row_item_course_hole_par_decrease) ) );
    }

    public static void increaseCourseHolePar(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_course_course_holes) )
            .perform(actionOnItemAtPosition(pos, clickNestedViewWithId(R.id.bttn_row_item_course_hole_par_increase) ) );
    }

    public static void matchCourseHolePar(final int pos, String par)
    {
        onView( withId(R.id.rcvw_fragment_course_course_holes) )
            .check( matches( atPositionOnView(pos, withText(par), R.id.txtv_row_item_course_hole_par) ) );
    }

    public static void matchCourseHoleTee(final int pos, String tee)
    {
        onView( withId(R.id.rcvw_fragment_course_course_holes) )
            .check( matches( atPositionOnView(pos, withText(tee), R.id.txtv_row_item_course_hole_tee) ) );
    }
}
