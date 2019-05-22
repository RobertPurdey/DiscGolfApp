package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.GameListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

public class WatchGameRequestActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_game_request);
        SetFragmentClick();
    }

    private void SetFragmentClick()
    {
        // todo: filter only watchable games
        GameListFragment fragment = (GameListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frag_watch_game_request_game_select_fragment);

        fragment.SetGameClickListener( (view, id) ->
            ActivityStarter.startWatchGame(this, id)
        );
    }
}