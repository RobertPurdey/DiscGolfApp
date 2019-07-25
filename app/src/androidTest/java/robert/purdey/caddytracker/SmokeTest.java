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
import static robert.purdey.caddytracker.UserActions.AccountInfoScreen.createAccount;
import static robert.purdey.caddytracker.UserActions.AccountInfoScreen.getFriendCode;
import static robert.purdey.caddytracker.UserActions.AccountInfoScreen.validateAccountInfo;
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
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.createFrolfGroup;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.matchGroupName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.sendInvite;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.setGroupName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.validateFrolfGroupRecord;
import static robert.purdey.caddytracker.UserActions.InviteScreen.acceptInvite;
import static robert.purdey.caddytracker.UserActions.InviteScreen.countInvites;
import static robert.purdey.caddytracker.UserActions.InviteScreen.matchInviteGroupName;
import static robert.purdey.caddytracker.UserActions.InviteScreen.matchInviteInviterName;
import static robert.purdey.caddytracker.UserActions.LoginScreen.login;
import static robert.purdey.caddytracker.UserActions.MainMenuScreen.goToAccountInfo;
import static robert.purdey.caddytracker.UserActions.MainMenuScreen.goToCreateAccount;
import static robert.purdey.caddytracker.UserActions.MainMenuScreen.goToGroupManager;
import static robert.purdey.caddytracker.UserActions.MainMenuScreen.goToInvites;
import static robert.purdey.caddytracker.UserActions.MainMenuScreen.goToManageGameScreen;
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.clickNewCourse;
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.countCourses;
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.goToCourse;
import static robert.purdey.caddytracker.UserActions.ManageGameScreen.goToNewGame;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.countGroups;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.goToFrolfGroup;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.goToNewGroupCreation;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.countHoleScores;
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
    private int pos1 = 1;

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
        createAccount(wpLoginName, wpHandle, wpPassword, wpConfPassword);       safeSleep(4000);

        // Christopher Robins creates his account
        goToCreateAccount();                                                    safeSleep(1000);
        createAccount(crLoginName, crHandle, crPassword, crConfPassword);       safeSleep(4000);

        // Christopher Robins logs in, his friend code is copied. Returns to the login screen.
        login(crLoginName, crPassword);                                         safeSleep(5000);
        goToAccountInfo();                                                      safeSleep(1000);
        validateAccountInfo(crLoginName, crHandle);                             safeSleep(1000);
        crFriendCode = getFriendCode();                                         safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);

        // Winnie the Pooh logs in, creates a new frolf group, A. A. Milne, and
        // invites Christopher Robins to it. Returns to the login screen.
        login(wpLoginName, wpPassword);                                         safeSleep(5000);
        goToGroupManager();                                                     safeSleep(1000);
        goToNewGroupCreation();                                                 safeSleep(1000);
        createFrolfGroup(frolfGroupNameA);                                      safeSleep(3000);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);
        goToGroupManager();                                                     safeSleep(1000);
        goToFrolfGroup(0);                                                      safeSleep(2000);
        sendInvite(crFriendCode);                                               safeSleep(3000);
        pressBack();                                                            safeSleep(2000);
        pressBack();                                                            safeSleep(2000);
        pressBack();                                                            safeSleep(2000);
        pressBack();                                                            safeSleep(2000);

        // Christopher Robins login and accepts invite. Returns to the login screen.
        login(crLoginName, crPassword);                                         safeSleep(5000);
        goToInvites();                                                          safeSleep(1000);
        matchInviteInviterName(pos0, wpHandle);                                 safeSleep(200);
        matchInviteGroupName(pos0, frolfGroupNameA);                            safeSleep(200);
        acceptInvite(pos0);                                                     safeSleep(1000);
        countInvites(0);                                                        safeSleep(200);
        pressBack();                                                            safeSleep(1000);
        pressBack();                                                            safeSleep(1000);

        // Winnie the Pooh logs in, goes to the created frolf group
        login(wpLoginName, wpPassword);                                         safeSleep(5000);
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
        pressBack();                                                            safeSleep(1000);
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
        pressBack();                                                            safeSleep(1000);
        matchGameName(gameNameA);                                               safeSleep(1000);
        matchSelectionText(selectGroupTxt);                                     safeSleep(1000);
        selectGroup(pos0);                                                      safeSleep(1000);
        matchSelectionText(selectCourseTxt);                                    safeSleep(1000);
        selectCourse(pos0);                                                     safeSleep(1000);
        matchSelectionText(selectPlayersTxt);                                   safeSleep(1000);
        selectPlayer(pos0);                                                     safeSleep(1000);
        selectPlayer(pos1);                                                     safeSleep(1000);
        goToStartGame();                                                        safeSleep(5000);

        // Validate game score screen
        matchGameCourseName(courseNameB);                                       safeSleep(200);
        countHoleScores(2);                                                     safeSleep(200);
        matchHoleLbl();                                                         safeSleep(200);
        matchHoleTee("1");                                                      safeSleep(200);
        matchParLbl();                                                          safeSleep(200);
        matchPar();                                                             safeSleep(200);

        goToNextHole();                                                         safeSleep(2000);

        countHoleScores(2);                                                     safeSleep(200);
        matchHoleLbl();                                                         safeSleep(200);
        matchHoleTee("2");                                                      safeSleep(200);
        matchParLbl();                                                          safeSleep(200);
        matchPar();                                                             safeSleep(200);
    }

    private void safeSleep(int duration)
    {
        try { Thread.sleep(duration); }
        catch (Exception ex) { }
    }
}
