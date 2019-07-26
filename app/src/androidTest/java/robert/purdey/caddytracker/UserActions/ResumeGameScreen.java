package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.atPositionOnView;

public class ResumeGameScreen
{
    public static void goToGame(int position)
    {
        onView( withId(R.id.rcvw_fragment_game_list) )
            .perform( actionOnItemAtPosition(position, click() ) );
    }

    public static void matchResumeGameName(final int pos, String expectedName)
    {
        onView( withId(R.id.rcvw_fragment_game_list) )
            .check( matches( atPositionOnView(pos, withText(expectedName), R.id.txtv_row_item_game_name) ) );
    }

    public static void matchResumeGameCourseName(final int pos, String expectedName)
    {
        onView( withId(R.id.rcvw_fragment_game_list) )
            .check( matches( atPositionOnView(pos, withText(expectedName), R.id.txtv_row_item_game_course_name) ) );
    }

    public static void matchResumeGameGroupName(final int pos, String expectedName)
    {
        onView( withId(R.id.rcvw_fragment_game_list) )
            .check( matches( atPositionOnView(pos, withText(expectedName), R.id.txtv_row_item_game_group_name) ) );
    }
}
