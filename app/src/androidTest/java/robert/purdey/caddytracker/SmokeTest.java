package robert.purdey.caddytracker;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import robert.purdey.caddytracker.ui.activities.LoginActivity;

import static androidx.test.espresso.Espresso.pressBack;
import static robert.purdey.caddytracker.UserActions.AccountInfoScreen.createAccount;
import static robert.purdey.caddytracker.UserActions.AccountInfoScreen.getFriendCode;
import static robert.purdey.caddytracker.UserActions.AccountInfoScreen.validateAccountInfo;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.clickAddCourseHole;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.clickRemoveCourseHole;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.clickSaveCourse;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.countHoles;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.decreaseCourseHolePar;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.increaseCourseHolePar;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.matchCourseHolePar;
import static robert.purdey.caddytracker.UserActions.CourseRecordScreen.matchCourseHoleTee;
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
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.countMembers;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.createFrolfGroup;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.matchGroupName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.matchMemberHandle;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.sendInvite;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.setGroupName;
import static robert.purdey.caddytracker.UserActions.FrolfGroupRecordScreen.validateFrolfGroupRecord;
import static robert.purdey.caddytracker.UserActions.InviteScreen.acceptInvite;
import static robert.purdey.caddytracker.UserActions.InviteScreen.countInvites;
import static robert.purdey.caddytracker.UserActions.InviteScreen.declineInvite;
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
import static robert.purdey.caddytracker.UserActions.ManageCourseScreen.validateCourseListItem;
import static robert.purdey.caddytracker.UserActions.ManageGameScreen.goToNewGame;
import static robert.purdey.caddytracker.UserActions.ManageGameScreen.goToResumeGame;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.countGroups;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.goToFrolfGroup;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.goToNewGroupCreation;
import static robert.purdey.caddytracker.UserActions.ManageGroupScreen.matchGroupListName;
import static robert.purdey.caddytracker.UserActions.ResumeGameScreen.goToGame;
import static robert.purdey.caddytracker.UserActions.ResumeGameScreen.matchResumeGameCourseName;
import static robert.purdey.caddytracker.UserActions.ResumeGameScreen.matchResumeGameGroupName;
import static robert.purdey.caddytracker.UserActions.ResumeGameScreen.matchResumeGameName;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.countGameHoleScores;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.decreaseGameHoleStrokes;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.goToNextGameHole;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.increaseGameHoleStrokes;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameCourseName;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameHoleLbl;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameHolePlayerHandle;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameHoleScoreScore;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameHoleStrokes;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameHoleTee;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGamePar;
import static robert.purdey.caddytracker.UserActions.ScoreGameScreen.matchGameParLbl;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class SmokeTest
{
    @Rule
    public ActivityTestRule<LoginActivity> mainMenuActivityRule = new ActivityTestRule<>(LoginActivity.class);

    // User A
    private String loginNameA    = "bbryyy";
    private String handleA       = "honey lover";
    private String passwordA     = "compassion";
    private String confPasswordA = "compassion";

    // User B
    private String loginNameB       = "mmarkkk";
    private String handleB          = "the dreamer";
    private String passwordB        = "adventure";
    private String confPasswordB    = "adventure";
    private String friendCodeB      = "";

    // User C
    private String loginNameC       = "ttayyy";
    private String handleC          = "slow and steady";
    private String passwordC        = "chiller";
    private String confPasswordC    = "chiller";
    private String friendCodeC      = "";

    // Expectations
    private int expGroupCount  = 1;

    // Positions
    private int pos0 = 0;
    private int pos1 = 1;

    // Frolf group info
    private String groupNameA = "Creations of A. A. Milne";
    private String frolfGroupNameB = "Emotions";

    // Course info
    private String courseNameA      = "The Sandy Pit";
    private String courseNameB      = "A Nice Place for Picnics";
    private int defaultHoleCount    = 9;
    private int expCourseCount      = 1;


    // Game info
    private String gameNameA         = "Pooh's Cup";
    private String selectGroupTxt    = "Select group";
    private String selectCourseTxt   = "Select course";
    private String selectPlayersTxt  = "Select players";

    @Test
    public void smokeTest()
    {
        // User A account creation
        goToCreateAccount();                                                                safeSleep(1000);
        createAccount(loginNameA, handleA, passwordA, confPasswordA);                       safeSleep(3000);

        // User B account creation
        goToCreateAccount();                                                                safeSleep(1000);
        createAccount(loginNameB, handleB, passwordB, confPasswordB);                       safeSleep(3000);

        // User C account creation
        goToCreateAccount();                                                                safeSleep(1000);
        createAccount(loginNameC, handleC, passwordC, confPasswordC);                       safeSleep(3000);

        // User B logs in, the friend code is copied. Returns to the login screen.
        login(loginNameB, passwordB);                                                       safeSleep(4000);
        goToAccountInfo();                                                                  safeSleep(1000);
        validateAccountInfo(loginNameB, handleB);
        friendCodeB = getFriendCode();
        pressBack();                                                                        safeSleep(500);
        pressBack();                                                                        safeSleep(500);

        // User C logs in, the friend code is copied. Returns to the login screen.
        login(loginNameC, passwordC);                                                       safeSleep(4000);
        goToAccountInfo();                                                                  safeSleep(1000);
        validateAccountInfo(loginNameC, handleC);
        friendCodeC = getFriendCode();
        pressBack();                                                                        safeSleep(500);
        pressBack();                                                                        safeSleep(500);

        // User A logs in, creates a new frolf group, A. A. Milne, and
        // invites User B and C. Returns to the login screen.
        login(loginNameA, passwordA);                                                       safeSleep(4000);
        goToGroupManager();                                                                 safeSleep(1000);
        goToNewGroupCreation();                                                             safeSleep(1000);
        createFrolfGroup(groupNameA);                                                       safeSleep(3000);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        goToGroupManager();                                                                 safeSleep(1000);
        matchGroupListName(pos0, groupNameA);
        goToFrolfGroup(0);                                                                  safeSleep(2000);
        matchGroupName(groupNameA);
        matchMemberHandle(0, handleA);
        countMembers(1);
        sendInvite(friendCodeB);                                                            safeSleep(500);
        sendInvite(friendCodeC);                                                            safeSleep(500);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);

        // User B login and accepts invite. Returns to the login screen.
        login(loginNameB, passwordB);                                                       safeSleep(4000);
        goToInvites();                                                                      safeSleep(1000);
        matchInviteInviterName(pos0, handleA);
        matchInviteGroupName(pos0, groupNameA);
        acceptInvite(pos0);                                                                 safeSleep(500);
        countInvites(0);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);

        // User C login and declines invite. Returns to the login screen.
        login(loginNameC, passwordC);                                                       safeSleep(4000);
        goToInvites();                                                                      safeSleep(1000);
        matchInviteInviterName(pos0, handleA);
        matchInviteGroupName(pos0, groupNameA);
        declineInvite(pos0);                                                                safeSleep(500);
        countInvites(0);                                                                    safeSleep(200);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);

        // User A logs in, goes to the created frolf group
        login(loginNameA, passwordA);                                                       safeSleep(4000);
        goToGroupManager();                                                                 safeSleep(1000);
        countGroups(expGroupCount);
        goToFrolfGroup(pos0);                                                               safeSleep(2000);
        validateFrolfGroupRecord(groupNameA, handleA, handleB);

        // Change group name and save
        setGroupName(frolfGroupNameB);                                                      safeSleep(200);
        clickSaveGroup();                                                                   safeSleep(2000);
        matchGroupName(frolfGroupNameB);                                                    safeSleep(200);

        // Create course
        clickCourses();                                                                     safeSleep(3000);
        clickNewCourse();                                                                   safeSleep(1000);
        setCourseName(courseNameA);                                                         safeSleep(2000);
        pressBack();                                                                        safeSleep(1000);

        // Check default hole tee
        countHoles(defaultHoleCount);
        matchCourseHoleTee(0, "1");
        matchCourseHoleTee(1, "2");
        matchCourseHoleTee(2, "3");
        matchCourseHoleTee(3, "4");
        matchCourseHoleTee(4, "5");
        matchCourseHoleTee(5, "6");
        matchCourseHoleTee(6, "7");
        matchCourseHoleTee(7, "8");
        matchCourseHoleTee(8, "9");

        // Check all pars default are 3
        matchCourseHolePar(0, "3");
        matchCourseHolePar(1, "3");
        matchCourseHolePar(2, "3");
        matchCourseHolePar(3, "3");
        matchCourseHolePar(4, "3");
        matchCourseHolePar(5, "3");
        matchCourseHolePar(6, "3");
        matchCourseHolePar(7, "3");
        matchCourseHolePar(8, "3");

        // Modify course to 2 holes. 1st hole par 2, 2nd hole par 4. Test add/remove buttons.
        clickAddCourseHole();                                                               safeSleep(200);
        countHoles(defaultHoleCount + 1);

        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);
        clickRemoveCourseHole();                                                            safeSleep(200);

        countHoles(defaultHoleCount - 7);

        matchCourseHoleTee(pos0, "1");
        matchCourseHoleTee(pos1, "2");

        decreaseCourseHolePar(pos0);                                                        safeSleep(200);
        matchCourseHolePar(pos0, "2");

        increaseCourseHolePar(pos1);                                                        safeSleep(200);
        matchCourseHolePar(pos1, "4");

        clickSaveCourse();                                                                  safeSleep(2000);
        matchCourseName(courseNameA);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);

        // Validate/check course can be found
        clickCourses();                                                                     safeSleep(2000);
        validateCourseListItem(pos0, courseNameA, "6", "2");
        countCourses(expCourseCount);
        goToCourse(pos0);                                                                   safeSleep(3000);
        matchCourseName(courseNameA);
        matchCourseHolePar(pos0, "2");
        matchCourseHolePar(pos1, "4");
        countHoles(defaultHoleCount - 7);
        setCourseName(courseNameB);
        clickSaveCourse();                                                                  safeSleep(3000);
        matchCourseName(courseNameB);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);

        // Create game and score holes (don't complete)
        goToManageGameScreen();                                                             safeSleep(1000);
        goToNewGame();                                                                      safeSleep(3000);
        setGameName(gameNameA);
        pressBack();                                                                        safeSleep(1000);
        matchGameName(gameNameA);
        matchSelectionText(selectGroupTxt);
        selectGroup(pos0);                                                                  safeSleep(500);
        matchSelectionText(selectCourseTxt);
        selectCourse(pos0);                                                                 safeSleep(500);
        matchSelectionText(selectPlayersTxt);
        selectPlayer(pos0);                                                                 safeSleep(500);
        selectPlayer(pos1);                                                                 safeSleep(500);
        goToStartGame();                                                                    safeSleep(3000);

        // Validate game score screen -- Hole 1
        matchGameCourseName(courseNameB);
        countGameHoleScores(2);
        matchGameHoleLbl();
        matchGameHoleTee("1");
        matchGameParLbl();
        matchGamePar("2");

        // Test hole scores before modifying -- Hole 1
        // User A
        matchGameHolePlayerHandle(0, handleA);
        matchGameHoleStrokes(0, "2");
        matchGameHoleScoreScore(0, "+0");

        // User B
        matchGameHolePlayerHandle(1, handleB);
        matchGameHoleStrokes(1, "2");
        matchGameHoleScoreScore(1, "+0");

        // Modify and test holes -- Hole 1
        // User A
        increaseGameHoleStrokes(0);                                                         safeSleep(500);
        matchGameHolePlayerHandle(0, handleA);
        matchGameHoleStrokes(0, "3");
        matchGameHoleScoreScore(0, "+1");

        // User B
        decreaseGameHoleStrokes(1);                                                         safeSleep(500);
        matchGameHolePlayerHandle(1, handleB);
        matchGameHoleStrokes(1, "1");
        matchGameHoleScoreScore(1, "-1");

        goToNextGameHole();                                                                 safeSleep(2000);

        // Validate game score screen -- Hole 2
        countGameHoleScores(2);
        matchGameHoleLbl();
        matchGameHoleTee("2");
        matchGameParLbl();
        matchGamePar("4");

        // Test hole scores before modifying -- Hole 2
        // User A
        matchGameHolePlayerHandle(0, handleA);
        matchGameHoleStrokes(0, "4");
        matchGameHoleScoreScore(0, "+0");

        // User B
        matchGameHolePlayerHandle(1, handleB);
        matchGameHoleStrokes(1, "4");
        matchGameHoleScoreScore(1, "+0");

        // Modify and test holes -- Hole 2
        // User A
        increaseGameHoleStrokes(0);                                                         safeSleep(200);
        increaseGameHoleStrokes(0);                                                         safeSleep(200);
        matchGameHolePlayerHandle(0, handleA);
        matchGameHoleStrokes(0, "6");
        matchGameHoleScoreScore(0, "+2");

        // User B
        decreaseGameHoleStrokes(1);                                                         safeSleep(200);
        decreaseGameHoleStrokes(1);                                                         safeSleep(200);
        matchGameHolePlayerHandle(1, handleB);
        matchGameHoleStrokes(1, "2");
        matchGameHoleScoreScore(1, "-2");

        // Back to Game Menu then to Resume Game screen and check game details
        pressBack();                                                                        safeSleep(1000);
        pressBack();                                                                        safeSleep(1000);
        goToResumeGame();                                                                   safeSleep(2000);
        matchResumeGameName(0, gameNameA);
        matchResumeGameCourseName(0, courseNameB);
        matchResumeGameGroupName(0, frolfGroupNameB);

        // Open game and check scores are where they left off
        goToGame(0);                                                                        safeSleep(2000);

        // Hole 1 where scores left off
        // User A
        matchGameHolePlayerHandle(0, handleA);
        matchGameHoleStrokes(0, "3");
        matchGameHoleScoreScore(0, "+1");

        // User B
        matchGameHolePlayerHandle(1, handleB);
        matchGameHoleStrokes(1, "1");
        matchGameHoleScoreScore(1, "-1");

        // To hole 2
        goToNextGameHole();                                                                 safeSleep(2000);

        // Hole 2 where scores left off (should be same as the scores were not saved)
        // User A
        matchGameHolePlayerHandle(0, handleA);
        matchGameHoleStrokes(0, "4");
        matchGameHoleScoreScore(0, "+0");

        // User B
        matchGameHolePlayerHandle(1, handleB);
        matchGameHoleStrokes(1, "4");
        matchGameHoleScoreScore(1, "+0");
    }

    private void safeSleep(int duration)
    {
        try { Thread.sleep(duration); }
        catch (Exception ex) { }
    }
}
