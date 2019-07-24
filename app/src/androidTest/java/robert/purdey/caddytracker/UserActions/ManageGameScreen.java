package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ManageGameScreen
{
    public static void goToNewGame()
    {
        onView( withId(R.id.bttn_activity_games_menu_new_game) )
            .perform( click() );
    }
}
