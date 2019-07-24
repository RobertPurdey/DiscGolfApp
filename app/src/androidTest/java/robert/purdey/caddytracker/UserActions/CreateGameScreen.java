package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.withIndex;

public class CreateGameScreen
{
    public static void matchGameName(String expectedGameName)
    {
        onView( withId(R.id.etxt_activity_new_game_game_name) )
            .check( matches( withText(expectedGameName) ) );
    }

    public static void setGameName(String gameName)
    {
        onView( withId(R.id.etxt_activity_new_game_game_name) )
            .perform( clearText() )
            .perform( typeText(gameName) );
    }

    public static void matchSelectionText(String expectedSelectionText)
    {
        onView( withId(R.id.txtv_activity_new_game_select_description) )
            .check( matches( withText(expectedSelectionText) ) );
    }

    public static void selectGroup(int position)
    {

        onView( withIndex(withId(R.id.rcvw_frolf_group_recycle_view), 0) )
            .perform( actionOnItemAtPosition(position, click() ) );
    }

    public static void selectCourse(int position)
    {
        onView( withIndex(withId(R.id.rcvw_fragment_course_list), 1) )
            .perform( actionOnItemAtPosition(position, click() ) );
    }

    public static void selectPlayer(int position)
    {
        onView( withIndex(withId(R.id.rcvw_fragment_frolf_group_members), 1) )
            .perform( actionOnItemAtPosition(position, click() ) );
    }

    public static void goToStartGame()
    {
        onView( withId(R.id.bttn_activity_new_game_create_new_game) )
            .perform( click() );
    }
}
