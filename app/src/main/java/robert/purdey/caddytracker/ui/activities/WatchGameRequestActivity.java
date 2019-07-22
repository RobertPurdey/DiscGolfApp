package robert.purdey.caddytracker.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.domain.games.GameFilter;
import robert.purdey.caddytracker.domain.games.GameState;
import robert.purdey.caddytracker.ui.fragments.GameListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

public class WatchGameRequestActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_game_request);
        setFragment();
    }

    private void setFragment()
    {
        GameListFragment fragment = (GameListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frag_watch_game_request_game_select_fragment);

        setFragmentGames(fragment);
        setFragmentClick(fragment);
    }

    private void setFragmentGames(GameListFragment fragment)
    {
        GameFilter filter = new GameFilter();
        filter.setState(GameState.InProgress);

        fragment.SetGames(filter);
    }

    private void setFragmentClick(GameListFragment fragment)
    {
        fragment.SetGameClickListener( (view, id) ->
            ActivityStarter.startWatchGame(this, id)
        );
    }
}