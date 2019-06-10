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
     * Start Game Menu activity
     *
     * @param view - view calling the method
     */
    public void onClickGamesMenu(View view)
    {
        ActivityStarter.startGamesMenu(this);
    }

    /**
     * Start Account activity
     *
     * @param view - view calling the method
     */
    public void onClickAccount(View view)
    {
        ActivityStarter.startAccountActivity(this);
    }

    /**
     * Start Manage Frolf Groups activity
     *
     * @param view - view calling the method
     */
    public void onClickManageFrolfGroups(View view)
    {
        ActivityStarter.startManageFrolfGroupsActivity(this);
    }

    /**
     * Start Manage Invites activity
     *
     * @param view - view calling the method
     */
    public void onClickManageInvites(View view)
    {
        ActivityStarter.startManageInvitesActivity(this);
    }

    /**
     * Start Help activity
     *
     * @param view - view calling the method
     */
    public void onClickHelp(View view)
    {
        ActivityStarter.startHelpActivity(this);
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
