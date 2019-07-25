package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MainMenuScreen
{
    public static void goToManageGameScreen()
    {
        onView( withId(R.id.bttn_activity_main_menu_games_menu) )
            .perform( click() );
    }

    public static void goToGroupManager()
    {
        onView( withId(R.id.bttn_activity_main_menu_manage_groups) )
            .perform( click() );
    }

    public static void goToAccountInfo()
    {
        onView( withId(R.id.bttn_activity_main_menu_account) )
            .perform( click() );
    }

    public static void goToInvites()
    {
        onView( withId(R.id.bttn_activity_main_menu_manage_invites) )
            .perform( click() );
    }

    public static void goToCreateAccount()
    {
        onView( withId(R.id.bttn_activity_login_create_account) )
            .perform( click() );
    }
}
