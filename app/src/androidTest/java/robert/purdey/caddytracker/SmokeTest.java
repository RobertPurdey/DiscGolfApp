package robert.purdey.caddytracker;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import robert.purdey.caddytracker.ui.activities.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.getText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SmokeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> mainMenuActivityRule
        = new ActivityTestRule<>(LoginActivity.class);

    // Winnie the Pooh
    private String wpLoginName     = "winnie";
    private String wpHandle        = "honey lover";
    private String wpPassword      = "compassion";
    private String wpConfPassword  = "compassion";
    private String wpFriendCode    = "";

    // Christopher Robins
    private String crLoginName     = "robins";
    private String crHandle        = "the dreamer";
    private String crPassword      = "adventure";
    private String crConfPassword  = "adventure";
    private String crFriendCode    = "";

    // Frolf group info
    private String frolfGroupName = "Creations of A. A. Milne";

    @Test
    public void smokeTest()
    {
        // Winnie the Pooh account creation
        //goToCreateAccount();
        //safeSleep(1000);
        //wpCreateAccount();
        //safeSleep(8000);

        // Christopher Robins account creation
        //goToCreateAccount();
        //safeSleep(1000);
        //crCreateAccount();
        //safeSleep(8000);

        // Christopher Robins login
        //crLogin();
        //safeSleep(6000);

        // Copy Christopher Robins friend code and go back to login screen
        //goToAccountInfo();
        //safeSleep(1000);
        //crValidateAccountInfo();
        //safeSleep(1000);
        //pressBack();
        //safeSleep(1000);
        //pressBack();
        //safeSleep(1000);

        // Winnie the Pooh login
        //wpLogin();
        //safeSleep(6000);

        // Create Creations of A. A. Milne group and back to groups list
        //goToGroupManager();
        //safeSleep(1000);
        //goToNewGroupCreation();
        //safeSleep(1000);
        //createFrolfGroup();
        //safeSleep(3000);
        //pressBack();

        // Send Group Invite and go back to login
        //goToFrolfGroup(0);
        //safeSleep(1000);
        //sendCrInvite();
        //safeSleep(3000);
        //pressBack();
        //safeSleep(1000);
        //pressBack();
        //safeSleep(1000);
        //pressBack();
        //safeSleep(1000);
        //pressBack();
        //safeSleep(1000);

        // Christopher Robins login
        crLogin();
        safeSleep(5000);

        // Go to invites
        goToInvites();
        safeSleep(1000);
    }

    private void safeSleep(int duration)
    {
        try { Thread.sleep(duration); }
        catch (Exception ex){ }
    }

    private void goToCreateAccount()
    {
        // click create account button
        onView( withId(R.id.bttn_activity_login_create_account) )
            .perform( click() );
    }

    private void wpCreateAccount()
    {
        // enter login name
        onView( withId(R.id.etxt_activity_create_account_loginname) )
            .perform( clearText() )
            .perform( typeText(wpLoginName) );

        // enter handle
        onView( withId(R.id.etxt_activity_create_account_handle) )
            .perform( clearText() )
            .perform( typeText(wpHandle) );

        // enter password
        onView( withId(R.id.etxt_activity_create_account_password) )
            .perform( clearText() )
            .perform( typeText(wpPassword) );

        // enter confirm password
        onView( withId(R.id.etxt_activity_create_account_confirm_password) )
            .perform( clearText() )
            .perform( typeText(wpConfPassword) );

        // send create account request
        onView( withId(R.id.bttn_activity_create_account_create) )
            .perform( click() );
    }

    private void wpLogin()
    {
        // enter login name
        onView( withId(R.id.etxt_activity_login_username) )
            .perform( clearText() )
            .perform( typeText(wpLoginName) );

        // enter password
        onView( withId(R.id.etxt_activity_login_password) )
            .perform( clearText() )
            .perform( typeText(wpPassword) );

        // login
        onView( withId(R.id.bttn_activity_login_login) )
            .perform( click() );
    }

    private void crCreateAccount()
    {
        // enter login name
        onView( withId(R.id.etxt_activity_create_account_loginname) )
            .perform( clearText() )
            .perform( typeText(crLoginName) );

        // enter handle
        onView( withId(R.id.etxt_activity_create_account_handle) )
            .perform( clearText() )
            .perform( typeText(crHandle) );

        // enter password
        onView( withId(R.id.etxt_activity_create_account_password) )
            .perform( clearText() )
            .perform( typeText(crPassword) );

        // enter confirm password
        onView( withId(R.id.etxt_activity_create_account_confirm_password) )
            .perform( clearText() )
            .perform( typeText(crConfPassword) );

        // send create account request
        onView( withId(R.id.bttn_activity_create_account_create) )
            .perform( clearText() )
            .perform( click() );
    }

    private void crLogin()
    {
        // enter login name
        onView( withId(R.id.etxt_activity_login_username) )
            .perform( clearText() )
            .perform( typeText(crLoginName) );

        // enter password
        onView( withId(R.id.etxt_activity_login_password) )
            .perform( clearText() )
            .perform( typeText(crPassword) );

        // login
        onView( withId(R.id.bttn_activity_login_login) )
            .perform( click() );
    }

    private void goToGroupManager()
    {
        // click manage groups button
        onView( withId(R.id.bttn_activity_main_menu_manage_groups) )
            .perform( click() );
    }

    private void goToNewGroupCreation()
    {
        // click new group button
        onView( withId(R.id.bttn_activity_manage_frolf_groups_new_frolf_group) )
            .perform( click() );
    }

    private void createFrolfGroup()
    {
        // enter frolf group name
        onView( withId(R.id.etxt_activity_frolf_group_record_group_name) )
            .perform( typeText(frolfGroupName) );

        // click create group button
        onView( withId(R.id.bttn_activity_frolf_group_record_create_frolf_group) )
            .perform( click() );
    }

    private void goToAccountInfo()
    {
        // click Account Info button
        onView( withId(R.id.bttn_activity_main_menu_account) )
            .perform( click() );
    }

    private void crValidateAccountInfo()
    {
        // login name matches
        onView( withId(R.id.etxt_activity_account_loginname) )
            .check( matches( withText(crLoginName) ) );

        // handle name matches
        onView( withId(R.id.etxt_activity_account_handle) )
            .check( matches( withText(crHandle) ) );

        // store friend code for invite purposes
        crFriendCode = getText( withId(R.id.etxt_activity_account_friend_code) );
    }

    private void sendCrInvite()
    {
        // enter Christopher Robins friend code
        onView( withId(R.id.etxt_activity_frolf_group_record_friend_code) )
            .perform( clearText() )
            .perform( typeText(crFriendCode) );

        // click send invite
        onView( withId(R.id.bttn_activity_frolf_group_record_add_friend) )
            .perform( click() );
    }

    private void goToInvites()
    {
        // click invites
        onView( withId(R.id.bttn_activity_main_menu_manage_invites) )
            .perform( click() );
    }

    private void goToFrolfGroup(int pos)
    {
        onView( withId(R.id.rcvw_frolf_group_recycle_view) )
            .perform( actionOnItemAtPosition(pos, click() ) );
    }
}
