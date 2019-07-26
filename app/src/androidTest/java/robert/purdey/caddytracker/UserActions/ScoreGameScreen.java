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
import static robert.purdey.caddytracker.EsspressoHelper.clickNestedViewWithId;

public class ScoreGameScreen
{
    public static void matchGameCourseName(String expectedGameName)
    {
        onView( withId(R.id.txtv_activity_score_game_course_name) )
            .check( matches( withText(expectedGameName) ) );
    }

    public static void matchGameHoleLbl()
    {
        onView( withId(R.id.txtv_activity_score_game_hole_lbl) )
            .check( matches( withText("Hole") ) );
    }

    public static void matchGameHoleTee(String expectedTee)
    {
        onView( withId(R.id.txtv_activity_score_game_current_hole) )
            .check( matches( withText(expectedTee) ) );
    }

    public static void matchGameParLbl()
    {
        onView( withId(R.id.txtv_activity_score_game_par_lbl) )
            .check( matches( withText("Par") ) );
    }

    public static void matchGamePar(String expectedPar)
    {
        onView( withId(R.id.txtv_activity_score_game_par) )
            .check( matches( withText(expectedPar) ) );
    }

    public static void goToPrevGameHole()
    {
        onView( withId(R.id.bttn_score_game_activity_prev_hole) )
            .perform( click() );
    }

    public static void goToNextGameHole()
    {
        onView( withId(R.id.bttn_score_game_activity_next_hole) )
            .perform( click() );
    }

    public static void countGameHoleScores(int expectedHoleScoreCount)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .check( new RecyclerViewItemCountAssertion(expectedHoleScoreCount) );
    }

    public static void decreaseGameHoleStrokes(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .perform(actionOnItemAtPosition(pos, clickNestedViewWithId(R.id.bttn_row_item_game_hole_score_strokes_decrease) ) );
    }

    public static void increaseGameHoleStrokes(final int pos)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .perform(actionOnItemAtPosition(pos, clickNestedViewWithId(R.id.bttn_row_item_game_hole_score_strokes_increase) ) );
    }

    public static void matchGameHolePlayerHandle(final int pos, String expectedHandle)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .check( matches( atPositionOnView(pos, withText(expectedHandle), R.id.txtv_row_item_game_hole_score_player_name) ) );
    }

    public static void matchGameHoleStrokes(final int pos, String expectedTee)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .check( matches( atPositionOnView(pos, withText(expectedTee), R.id.txtv_row_item_game_hole_score_strokes) ) );
    }

    public static void matchGameHoleScoreScore(final int pos, String expectedScore)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .check( matches( atPositionOnView(pos, withText(expectedScore), R.id.txtv_row_item_game_hole_score_score) ) );
    }
}
