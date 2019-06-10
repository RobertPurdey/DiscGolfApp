package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GamesMenuActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_menu);
    }

    /**
     * Start New Game activity
     *
     * @param view - view calling the method
     */
    public void onClickNewGame(View view)
    {
        ActivityStarter.startNewGameActivity(this);
    }

    /**
     * Start Resume Game activity
     *
     * @param view - view calling the method
     */
    public void onClickResumeGame(View view)
    {
        ActivityStarter.startResumeGameActivity(this);
    }

    /**
     * Start Game Results Game activity
     *
     * @param view - view calling the method
     */
    public void onClickGameResults(View view)
    {
        ActivityStarter.startGameResultsActivity(this);
    }

    /**
     * Start Manage Invites activity
     *
     * @param view - view calling the method
     */
    public void onClickWatchGameRequest(View view)
    {
        ActivityStarter.startWatchGameRequest(this);
    }

}
