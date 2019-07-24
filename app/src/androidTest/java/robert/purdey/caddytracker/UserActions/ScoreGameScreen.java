package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class ScoreGameScreen
{
    public static void matchGameCourseName(String expectedGameName)
    {
        onView( withId(R.id.txtv_activity_score_game_course_name) )
            .check( matches( withText(expectedGameName) ) );
    }

    public static void matchHoleLbl()
    {
        onView( withId(R.id.txtv_activity_score_game_hole_lbl) )
            .check( matches( withText("Hole") ) );
    }

    public static void matchHoleTee(String expectedTee)
    {
        onView( withId(R.id.txtv_activity_score_game_current_hole) )
            .check( matches( withText(expectedTee) ) );
    }

    public static void matchParLbl()
    {
        onView( withId(R.id.txtv_activity_score_game_par_lbl) )
            .check( matches( withText("Par") ) );
    }

    public static void matchPar()
    {
        onView( withId(R.id.txtv_activity_score_game_par) )
            .check( matches( withText("3") ) );
    }

    public static void goToPrevHole()
    {
        onView( withId(R.id.bttn_score_game_activity_prev_hole) )
            .perform( click() );
    }

    public static void goToNextHole()
    {
        onView( withId(R.id.bttn_score_game_activity_next_hole) )
            .perform( click() );
    }

    public static void countHoleScores(int expectedHoleScoreCount)
    {
        onView( withId(R.id.rcvw_fragment_game_hole_scores) )
            .check( new RecyclerViewItemCountAssertion(expectedHoleScoreCount) );
    }
}
