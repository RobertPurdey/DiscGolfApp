package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.atPositionOnView;

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

    public static void validateCourseListItem(
        final int pos,
        String expectedCourseName,
        String expectedPar,
        String expectedHoleCount)
    {
        matchCourseListName(pos, expectedCourseName);
        matchCourseListParLbl(pos);
        matchCourseListPar(pos, expectedPar);
        matchCourseListHoleCountLbl(pos);
        matchCourseListHoleCount(pos, expectedHoleCount);
    }

    public static void matchCourseListName(final int pos, String courseName)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .check( matches( atPositionOnView(pos, withText(courseName), R.id.txtv_row_item_course_name) ) );
    }

    public static void matchCourseListParLbl(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .check( matches( atPositionOnView(pos, withText("Par"), R.id.txtv_row_item_course_par_label) ) );
    }

    public static void matchCourseListPar(final int pos, String expectedPar)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .check( matches( atPositionOnView(pos, withText(expectedPar), R.id.txtv_row_item_course_par) ) );
    }

    public static void matchCourseListHoleCountLbl(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .check( matches( atPositionOnView(pos, withText("Holes"), R.id.txtv_row_item_course_hole_count_label) ) );
    }

    public static void matchCourseListHoleCount(final int pos, String expectedHoleCount)
    {
        onView( withId(R.id.rcvw_fragment_course_list) )
            .check( matches( atPositionOnView(pos, withText(expectedHoleCount), R.id.txtv_row_item_course_hole_count) ) );
    }
}
