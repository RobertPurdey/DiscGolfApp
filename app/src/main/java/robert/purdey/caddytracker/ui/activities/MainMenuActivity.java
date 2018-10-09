package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

public class MainMenuActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    /**
     * Start New Game activity
     *
     * @param view - view calling the method
     */
    public void onClickNewGame(View view)
    {
        //ActivityStarter.startNewGameActivity(this);
    }

    /**
     * Start Resume Game activity
     *
     * @param view - view calling the method
     */
    public void onClickResumeGame(View view)
    {
        //ActivityStarter.startResumeGameActivity(this);
    }

    /**
     * Start Manage Courses activity
     *
     * @param view - view calling the method
     */
    public void onClickManageCourses(View view)
    {
        //ActivityStarter.startManageCoursesActivity(this);
    }

    /**
     * Start Manage Players activity
     *
     * @param view - view calling the method
     */
    public void onClickManageFriends(View view)
    {
        ActivityStarter.startManageFriendsActivity(this);
    }

    /**
     * Start Help activity
     *
     * @param view - view calling the method
     */
    public void onClickHelp(View view)
    {
        //ActivityStarter.startHelpActivity(this);
    }

    /**
     * Start Manage Players activity
     *
     * @param view - view calling the method
     */
    public void onClickStats(View view)
    {

    }
}
