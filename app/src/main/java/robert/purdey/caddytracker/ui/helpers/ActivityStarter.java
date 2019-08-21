package robert.purdey.caddytracker.ui.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.Nullable;

import java.util.UUID;

import robert.purdey.caddytracker.ui.activities.AccountActivity;
import robert.purdey.caddytracker.ui.activities.CourseRecordActivity;
import robert.purdey.caddytracker.ui.activities.CreateAccountActivity;
import robert.purdey.caddytracker.ui.activities.FrolfGroupRecordActivity;
import robert.purdey.caddytracker.ui.activities.GameResultsActivity;
import robert.purdey.caddytracker.ui.activities.GamesMenuActivity;
import robert.purdey.caddytracker.ui.activities.LoginActivity;
import robert.purdey.caddytracker.ui.activities.MainMenuActivity;
import robert.purdey.caddytracker.ui.activities.ManageCoursesActivity;
import robert.purdey.caddytracker.ui.activities.ManageFrolfGroupsActivity;
import robert.purdey.caddytracker.ui.activities.ManageInvitesActivity;
import robert.purdey.caddytracker.ui.activities.NewGameActivity;
import robert.purdey.caddytracker.ui.activities.ResumeGameActivity;
import robert.purdey.caddytracker.ui.activities.ScoreCardActivity;
import robert.purdey.caddytracker.ui.activities.ScoreGameActivity;
import robert.purdey.caddytracker.ui.activities.WatchGameActivity;
import robert.purdey.caddytracker.ui.activities.WatchGameRequestActivity;

/**
 *
 * Helper methods for activities
 */
public class ActivityStarter
{
    /**
     * Starts Main Menu Activity
     *
     * @param context
     */
    public static void startMainMenuActivity(Context context)
    {
        startActivity(context, MainMenuActivity.class);
    }

    /**
     * Starts New Game Activity
     *
     * @param context
     */
    public static void startNewGameActivity(Context context)
    {
        startActivity(context, NewGameActivity.class);
    }

    /**
     * Starts Resume Game Activity
     *
     * @param context
     */
    public static void startResumeGameActivity(Context context)
    {
        startActivity(context, ResumeGameActivity.class);
    }

    /**
     * Starts  Game Results Activity
     *
     * @param context
     */
    public static void startGameResultsActivity(Context context)
    {
        startActivity(context, GameResultsActivity.class);
    }

    /**
     * Starts Watch Game Request Activity
     *
     * @param context
     */
    public static void startWatchGameRequest(Context context)
    {
        startActivity(context, WatchGameRequestActivity.class);
    }

    /**
     * Starts Games Menu Activity
     *
     * @param context
     */
    public static void startGamesMenu(Context context)
    {
        startActivity(context, GamesMenuActivity.class);
    }

    /**
     * Starts Add Player Activity
     *
     * @param context
     */
    public static void startWatchGame(Context context, UUID recordId)
    {
        Intent intent = new Intent(context, WatchGameActivity.class);
        String id     = "";

        if ( recordId != null )
        {
            id = recordId.toString();

            intent.putExtra(WatchGameActivity.RECORD_ID, id);
            context.startActivity(intent);
        }
    }

    /**
     * Starts Account Activity
     *
     * @param context
     */
    public static void startAccountActivity(Context context)
    {
        startActivity(context, AccountActivity.class);
    }

    /**
     * Starts Help Activity
     *
     * @param context
     */
    public static void startHelpActivity(Context context)
    {

    }

    /**
     * Starts Create Account Activity
     *
     * @param context
     */
    public static void startCreateAccountActivity(Context context)
    {
        startActivity(context, CreateAccountActivity.class);
    }

    /**
     * Starts Login Activity
     *
     * @param context
     */
    public static void startLoginAcitvity(Context context)
    {
        startActivity(context, LoginActivity.class);
    }

    /**
     * Starts Manage Frolf Groups Activity
     *
     * @param context
     */
    public static void startManageFrolfGroupsActivity(Context context)
    {
        startActivity(context, ManageFrolfGroupsActivity.class);
    }

    /**
     * Starts Frolf Group Record Activity
     *
     * @param context
     */
    public static void startFrolfGroupRecordActivity(Context context, UUID recordId, boolean isNew)
    {
        Intent intent = new Intent(context, FrolfGroupRecordActivity.class);
        String id           = "";
        String isNewExtra   = isNew ? "t" : "f";

        if ( recordId != null )
        {
            id = recordId.toString();
        }



        intent.putExtra(FrolfGroupRecordActivity.RECORD_ID, id);
        intent.putExtra(FrolfGroupRecordActivity.IS_NEW, isNewExtra);
        context.startActivity(intent);
    }

    /**
     * Starts Course Record Activity
     *
     * @param context
     */
    public static void startManageCoursesActivity(
        Context context,
        UUID frolfGroupRecordId)
    {
        Intent intent       = new Intent(context, ManageCoursesActivity.class);
        String frolfGroupId = "";

        if ( frolfGroupRecordId != null )
        {
            frolfGroupId = frolfGroupRecordId.toString();
        }

        intent.putExtra(ManageCoursesActivity.FROLF_GROUP_ID, frolfGroupId);

        context.startActivity(intent);
    }

    /**
     * Starts Course Record Activity
     *
     * @param context
     */
    public static void startCourseRecordActivity(
        Context context,
        @Nullable UUID recordId,
        @Nullable UUID frolfGroupRecordId)
    {
        Intent intent       = new Intent(context, CourseRecordActivity.class);
        String id           = "";
        String frolfGroupId = "";

        if ( recordId != null )
        {
            id = recordId.toString();
        }

        if ( frolfGroupRecordId != null )
        {
            frolfGroupId = frolfGroupRecordId.toString();
        }

        intent.putExtra(CourseRecordActivity.RECORD_ID, id);
        intent.putExtra(CourseRecordActivity.FROLF_GROUP_ID, frolfGroupId);

        context.startActivity(intent);
    }

    /**
     * Starts Manage Invites Activity
     *
     * @param context
     */
    public static void startManageInvitesActivity(Context context)
    {
        startActivity(context, ManageInvitesActivity.class);
    }

    /**todo: rename to score
     * Starts Sacore Game Activity loading the game for the id passed in as gameId
     *
     * @param context
     * @param gameId - id of game to score
     */
    public static void startScoreGameActivity(Context context, UUID gameId)
    {
        // todo: throw error here if no game id? you cant score without finding a game

        Intent intent    = new Intent(context, ScoreGameActivity.class);
        String id        = "";

        if ( gameId != null )
        {
            id = gameId.toString();
        }

        intent.putExtra(ScoreGameActivity.RECORD_ID, id);
        context.startActivity(intent);
    }

    /**
     * Starts Score Card Activity loading the game for the id passed in as gameId
     *
     * @param context
     * @param gameId - id of game to score
     */
    public static void startScoreCardActivity(Context context, UUID gameId, boolean isRefresh)
    {
        // todo: throw error here if no game id? you cant score without finding a game

        Intent intent    = new Intent(context, ScoreCardActivity.class);
        String id        = "";
        String refresh   = "";

        if ( gameId != null )
        {
            id = gameId.toString();
        }

        if (isRefresh)
        {
            refresh = "refresh";
        }

        intent.putExtra(ScoreCardActivity.RECORD_ID, id);
        intent.putExtra("IS_REFRESH", refresh);
        context.startActivity(intent);
    }

    /**
     * starts an Activity
     *
     * @param context
     * @param activityClass activity class to start
     */
    public static void startActivity(Context context, Class<? extends Activity> activityClass)
    {
        Intent intent = new Intent(context, activityClass);
        context.startActivity(intent);
    }
}
