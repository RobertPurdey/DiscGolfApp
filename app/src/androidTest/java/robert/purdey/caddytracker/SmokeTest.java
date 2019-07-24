package robert.purdey.caddytracker;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import robert.purdey.caddytracker.ViewAssertions.RecyclerViewItemCountAssertion;
import robert.purdey.caddytracker.ui.activities.LoginActivity;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static robert.purdey.caddytracker.EsspressoHelper.clickNestedViewWithId;
import static robert.purdey.caddytracker.EsspressoHelper.getText;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.clickAddHole;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.clickRemoveHole;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.clickSaveCourse;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.countHoles;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.matchCourseName;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.setCourseName;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.goToStartGame;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.matchGameName;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.matchSelectionText;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.selectCourse;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.selectGroup;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.selectPlayer;
import static robert.purdey.caddytracker.UserActions.CreateGameScreen.setGameName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.clickCourses;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.clickSaveGroup;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.matchGroupName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.setGroupName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.validateFrolfGroupRecord;
import static robert.purdey.caddytracker.UserActions.MainMenuScreen.goToManageGameScreen;
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.clickNewCourse;
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.countCourses;
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.goToCourse;
import static robert.purdey.caddytracker.UserActions.ManageGameScreen.goToNewGame;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.countGroups;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.goToFrolfGroup;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.goToNewGroupCreation;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.goToNextHole;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameCourseName;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchHoleLbl;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchHoleTee;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchPar;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchParLbl;

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

    // Expectations
    private int expGroupCount  = 1;
    private int expMemberCount = 2;

    // Positions
    private int pos0 = 0;

    // Frolf group info
    private String frolfGroupNameA = "Creations of A. A. Milne";
    private String frolfGroupNameB = "Emotions";

    // Course info
    private String courseNameA      = "The Sandy Pit";
    private String courseNameB      = "A Nice Place for Picnics";
    private int defaultHoleCount    = 9;
    private int expHoleCount        = 3;
    private int expCourseCount      = 1;


    // Game info
    private String gameNameA         = "Pooh's Cup";
    private String selectGroupTxt    = "Select group";
    private String selectCourseTxt   = "Select course";
    private String selectPlayersTxt  = "Select players";

    @Test
    public void smokeTest()
    {
        // Winnie the Pooh creates his account
        goToCreateAccount();                                                    safeSleep(1000);
        wpCreateAccount();                                                      safeSleep(4000);

        // Christopher Robins creates his account
        goToCreateAccount();                                                    safeSleep(1000);
        crCreateAccount();                                                      safeSleep(4000);

        // Christopher Robins logs in, his friend code is copied. Returns to the login screen.
        crLogin();                                                              safeSleep(5000);
        goToAccountInfo();                                                      safeSleep(1000);
        crValidateAccountInfo();                                                safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);

        // Winnie the Pooh logs in, creates a new frolf group, A. A. Milne, and
        // invites Christopher Robins to it. Returns to the login screen.
        wpLogin();                                                              safeSleep(5000);
        goToGroupManager();                                                     safeSleep(1000);
        goToNewGroupCreation();                                                 safeSleep(1000);
        createFrolfGroup();                                                     safeSleep(3000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        goToGroupManager();                                                     safeSleep(1000);
        goToFrolfGroup(0);                                                      safeSleep(2000);
        sendCrInvite();                                                         safeSleep(3000);
        pressBack();                                                            safeSleep(2000);
        pressBack();                                                            safeSleep(2000);
        pressBack();                                                            safeSleep(2000);
        pressBack();                                                            safeSleep(2000);

        // Christopher Robins login and accepts invite. Returns to the login screen.
        crLogin();                                                              safeSleep(5000);
        goToInvites();                                                          safeSleep(1000);
        acceptInviteFromWp();                                                   safeSleep(3000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);

        // Winni the Pooh logs in, goes to the created frolf group
        wpLogin();                                                              safeSleep(5000);
        goToGroupManager();                                                     safeSleep(2000);
        countGroups(expGroupCount);                                             safeSleep(1000);
        goToFrolfGroup(pos0);                                                   safeSleep(3000);
        validateFrolfGroupRecord(frolfGroupNameA, expMemberCount);              safeSleep(1000);

        // Change group name and save
        setGroupName(frolfGroupNameB);                                          safeSleep(1000);
        clickSaveGroup();                                                       safeSleep(3000);
        matchGroupName(frolfGroupNameB);                                        safeSleep(200);

        // Create course
        clickCourses();                                                         safeSleep(3000);
        clickNewCourse();                                                       safeSleep(1000);
        setCourseName(courseNameA);                                             safeSleep(200);
        countHoles(defaultHoleCount);                                           safeSleep(200);
        clickAddHole();                                                         safeSleep(200);
        countHoles(defaultHoleCount + 1);                                       safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        clickRemoveHole();                                                      safeSleep(200);
        countHoles(defaultHoleCount - 7);                                       safeSleep(200);
        clickSaveCourse();                                                      safeSleep(2000);
        matchCourseName(courseNameA);                                           safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);

        // Check course can be found
        clickCourses();                                                         safeSleep(1000);
        countCourses(expCourseCount);                                           safeSleep(1000);
        goToCourse(pos0);                                                       safeSleep(3000);
        matchCourseName(courseNameA);                                           safeSleep(1000);
        countHoles(defaultHoleCount - 7);                                       safeSleep(1000);
        setCourseName(courseNameB);                                             safeSleep(1000);
        clickSaveCourse();                                                      safeSleep(3000);
        matchCourseName(courseNameB);                                           safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);

        // Create game and score holes (don't complete)
        goToManageGameScreen();                                                 safeSleep(1000);
        goToNewGame();                                                          safeSleep(3000);
        setGameName(gameNameA);                                                 safeSleep(1000);
        matchGameName(gameNameA);                                               safeSleep(1000);
        matchSelectionText(selectGroupTxt);                                     safeSleep(1000);
        selectGroup(pos0);                                                      safeSleep(1000);
        matchSelectionText(selectCourseTxt);                                    safeSleep(1000);
        selectCourse(pos0);                                                     safeSleep(1000);
        matchSelectionText(selectPlayersTxt);                                   safeSleep(1000);
        selectPlayer(pos0);                                                     safeSleep(1000);
        goToStartGame();                                                        safeSleep(5000);

        // Validate game score screen
        matchGameCourseName(courseNameB);                                       safeSleep(1000);
        matchHoleLbl();                                                         safeSleep(1000);
        matchHoleTee("1");                                                      safeSleep(1000);
        matchParLbl();                                                          safeSleep(1000);
        matchPar();                                                             safeSleep(1000);
        goToNextHole();                                                         safeSleep(2000);
    }

    private void safeSleep(int duration)
    {
        try { Thread.sleep(duration); }
        catch (Exception ex) { }
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

    private void createFrolfGroup()
    {
        // enter frolf group name
        onView( withId(R.id.etxt_activity_frolf_group_record_group_name) )
            .perform( typeText(frolfGroupNameA) );

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

    private void acceptInviteFromWp()
    {
        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .perform( clickNestedViewWithId(R.id.bttn_row_item_frolf_group_invite_accept_invite) );

        safeSleep(200);

        onView( withId(R.id.rcvw_fragment_frolf_group_invites) )
            .check( new RecyclerViewItemCountAssertion(0) );
    }
}
