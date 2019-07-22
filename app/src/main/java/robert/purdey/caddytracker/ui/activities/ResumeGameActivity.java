package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.domain.games.GameFilter;
import robert.purdey.caddytracker.domain.games.GameState;
import robert.purdey.caddytracker.ui.fragments.GameListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResumeGameActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);
        setFragment();
    }

    private void setFragment()
    {
        GameListFragment fragment = (GameListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frag_resume_game_select_fragment);

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
            ActivityStarter.startScoreGameActivity(this, id)
        );
    }
}
