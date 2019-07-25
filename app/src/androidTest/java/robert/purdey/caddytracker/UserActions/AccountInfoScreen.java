package robert.purdey.caddytracker.UserActions;

import robert.purdey.caddytracker.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.getText;

public class AccountInfoScreen
{
    public static void validateAccountInfo(String expectedLoginName, String expectedHandle)
    {
        matchLoginName(expectedLoginName);
        matchHandle(expectedHandle);
    }

    public static void createAccount(String loginName, String handle, String password, String confPassword)
    {
        setLoginName(loginName);
        setHandle(handle);
        setPassword(password);
        setConfPassword(confPassword);

        clickCreateAccount();
    }

    public static void matchLoginName(String expectedLoginName)
    {
        onView( withId(R.id.etxt_activity_account_loginname) )
            .check( matches( withText(expectedLoginName) ) );
    }

    public static void matchHandle(String expectedHandle)
    {
        onView( withId(R.id.etxt_activity_account_handle) )
            .check( matches( withText(expectedHandle) ) );
    }

    public static String getFriendCode()
    {
        return getText( withId(R.id.etxt_activity_account_friend_code) );
    }

    public static void setLoginName(String loginName)
    {
        onView( withId(R.id.etxt_activity_create_account_loginname) )
            .perform( clearText() )
            .perform( typeText(loginName) );
    }

    public static void setHandle(String handle)
    {
        onView( withId(R.id.etxt_activity_create_account_handle) )
            .perform( clearText() )
            .perform( typeText(handle) );
    }

    public static void setPassword(String password)
    {
        onView( withId(R.id.etxt_activity_create_account_password) )
            .perform( clearText() )
            .perform( typeText(password) );
    }

    public static void setConfPassword(String confPassword)
    {
        onView( withId(R.id.etxt_activity_create_account_confirm_password) )
            .perform( clearText() )
            .perform( typeText(confPassword) );
    }

    public static void clickCreateAccount()
    {
        onView( withId(R.id.bttn_activity_create_account_create) )
            .perform( click() );
    }
}
