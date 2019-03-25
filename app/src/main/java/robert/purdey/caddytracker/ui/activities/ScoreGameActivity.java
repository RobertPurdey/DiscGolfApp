package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.GameHoleScoresFragment;
import robert.purdey.caddytracker.ui.viewmodels.ScoreGameActivityViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

public class ScoreGameActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    private ScoreGameActivityViewModel viewModel;

    public ScoreGameActivity()
    {

    }

    // todo: look at frolfGroupRecord for more ideas on how to bind
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_game);

        // Attempt to get id.
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(ScoreGameActivity.RECORD_ID);
        viewModel       = ViewModelProviders.of(this).get(ScoreGameActivityViewModel.class);

        // todo: throw error if one isnt given? cant score no game :D

        // get record data when set
        if ( !recordId.equals("") )
        {
            viewModel.getGame(UUID.fromString(recordId)).observe(this, gameModel ->
                LoadHoleScores(gameModel.getIdKey(), 1)
            );
        }
    }

    private void LoadHoleScores(UUID gameId, int holeNumber)
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();
        viewModel.setCurrentHole(holeNumber);
        fragment.Load(gameId, holeNumber);
    }

    public void onNextHoleClick(View view)
    {
        SaveCurrentHoles();
        int nextHole = viewModel.getNextHoleNumber();

        // Load new holes until next hole is the last one
        if (nextHole <= viewModel.Game.getValue().getHoleIds().size())
        {
            LoadHoleScores(viewModel.Game.getValue().getIdKey(), nextHole);
        }
    }

    public void onPrevHoleClick(View view)
    {
        SaveCurrentHoles();
        int prevHole = viewModel.getPrevHoleNumber();

        // Load new holes until next hole is the last one
        if (prevHole > 0)
        {
            LoadHoleScores(viewModel.Game.getValue().getIdKey(), prevHole);
        }

    }

    private void SaveCurrentHoles()
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();

        if (fragment != null)
        {
            viewModel.SaveHoleScores(fragment.getHoleScores());
        }
    }

    private GameHoleScoresFragment getGameHoleScoreFrag()
    {
        return (GameHoleScoresFragment)
            getSupportFragmentManager().findFragmentById(R.id.frag_mng_game_hole_scores_fragment);
    }
}
