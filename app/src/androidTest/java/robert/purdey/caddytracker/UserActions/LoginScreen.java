package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class LoginScreen
{
    public static void login(String loginName, String password)
    {
        setLoginName(loginName);
        setPassword(password);

        clickLogin();
    }

    public static void clickLogin()
    {
        onView( withId(R.id.bttn_activity_login_login) )
            .perform( click() );
    }

    public static void setLoginName(String loginName)
    {
        onView( withId(R.id.etxt_activity_login_username) )
            .perform( clearText() )
            .perform( typeText(loginName) );
    }

    public static void setPassword(String password)
    {
        onView( withId(R.id.etxt_activity_login_password ) )
            .perform( clearText() )
            .perform( typeText(password) );
    }
}
