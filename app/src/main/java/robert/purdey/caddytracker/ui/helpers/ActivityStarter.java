package robert.purdey.caddytracker.ui.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import robert.purdey.caddytracker.ui.activities.MainMenuActivity;
import robert.purdey.caddytracker.ui.activities.ManageFriendsActivity;
import robert.purdey.caddytracker.ui.activities.ManageFrolfGroupsActivity;

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
        //startActivity(context, NewGameActivity.class);
    }

    /**
     * Starts Resume Game Activity
     *
     * @param context
     */
    public static void startResumeGameActivity(Context context)
    {
        //startActivity(context, ResumeGameActivity.class);
    }

    /**
     * Starts Manage Courses Activity
     *
     * @param context
     */
    public static void startManageCoursesActivity(Context context)
    {
        //startActivity(context, ManageCoursesActivity.class);
    }

    /**
     * Starts Manage Players Activity
     *
     * @param context
     */
    public static void startManageFriendsActivity(Context context)
    {
        startActivity(context, ManageFriendsActivity.class);
    }

    /**
     * Starts Add Player Activity
     *
     * @param context
     */
    public static void startAddPlayerActivity(Context context)
    {
        //startActivity(context, AddPlayerActivity.class);
    }

    /**
     * Starts Help Activity
     *
     * @param context
     */
    public static void startHelpActivity(Context context)
    {
        //startActivity(context, HelpActivity.class);
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
     * Starts Score Game Activity loading the game for the id passed in as gameId
     *
     * @param context
     * @param gameId - id of game to score
     */
    public static void startScoreGameActivity(Context context, long gameId)
    {
        //Intent intent = new Intent(context, ScoreGameActivity.class);
        //intent.putExtra(ScoreGameActivity.GAME_PKEY_TAG, gameId);

        //context.startActivity(intent);
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
