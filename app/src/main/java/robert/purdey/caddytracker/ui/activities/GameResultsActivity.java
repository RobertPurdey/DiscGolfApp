package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.domain.games.GameFilter;
import robert.purdey.caddytracker.domain.games.GameState;
import robert.purdey.caddytracker.ui.fragments.GameListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

public class GameResultsActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);
        setFragment();
    }
    private void setFragment()
    {
        GameListFragment fragment = (GameListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frag_game_results_select_fragment);

        setFragmentGames(fragment);
        setFragmentClick(fragment);
    }

    private void setFragmentGames(GameListFragment fragment)
    {
        GameFilter filter = new GameFilter();
        filter.setState(GameState.Completed);

        fragment.SetGames(filter);
    }

    private void setFragmentClick(GameListFragment fragment)
    {
        fragment.SetGameClickListener( (view, id) ->
            ActivityStarter.startScoreCardActivity(this, id)
        );
    }
}