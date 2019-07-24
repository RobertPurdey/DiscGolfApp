package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class CourseRecordScreen
{
    public static void countHoles(int expectedHoleCount)
    {
        onView( withId(R.id.rcvw_fragment_course_course_holes) )
            .check( new RecyclerViewItemCountAssertion(expectedHoleCount) );
    }

    public static void clickAddHole()
    {
        onView( withId(R.id.bttn_course_record_add_hole) )
            .perform( click() );
    }

    public static void clickRemoveHole()
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
}
