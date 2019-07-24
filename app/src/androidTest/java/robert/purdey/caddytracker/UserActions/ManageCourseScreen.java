package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ManageCourseScreen
{
    public static void clickNewCourse()
    {
        onView( withId(R.id.bttn_activity_manage_courses_new_course) )
            .perform( click() );
    }

    public static void countCourses(int expectedCourseCount)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .check( new RecyclerViewItemCountAssertion(expectedCourseCount) );
    }

    public static void goToCourse(int position)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .perform( actionOnItemAtPosition(position, click() ) );
    }
}
