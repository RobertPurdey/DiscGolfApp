package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;

import robert.purdey.caddytracker.databinding.ActivityScoreGameBinding;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.fragments.GameHoleScoresFragment;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.viewmodels.ScoreGameActivityViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.UUID;

public class ScoreGameActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    private ScoreGameActivityViewModel scoreGameActivViewModel;

    public ScoreGameActivity()
    {

    }

    // todo: look at frolfGroupRecord for more ideas on how to bind
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ActivityScoreGameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_score_game);
        scoreGameActivViewModel          = ViewModelProviders.of(this).get(ScoreGameActivityViewModel.class);

        binding.setScoreGameActivViewModel(scoreGameActivViewModel);
        binding.setLifecycleOwner(this);

        // Attempt to get id.
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(ScoreGameActivity.RECORD_ID);

        // todo: throw error if one isnt given? cant score no game :D

        // get record data when set
        if ( !recordId.equals("") )
        {
            scoreGameActivViewModel.getGame(UUID.fromString(recordId)).observe(this, gameModel -> {
                TextView courseName = findViewById(R.id.txtv_activity_score_course_name);
                courseName.setText(gameModel.getCourseName());
                LoadHoleScores(gameModel.getIdKey(), 1);
            });
        }
    }

    private void LoadHoleScores(UUID gameId, int holeNumber)
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();
        scoreGameActivViewModel.setCurrentHole(holeNumber);
        fragment.Load(gameId, holeNumber);
    }

    public void onNextHoleClick(View view)
    {
        int nextHole = scoreGameActivViewModel.getNextHoleNumber();
        saveCurrentHoles(nextHole);

        // Load new holes until next hole is the last one
       // if (nextHole <= viewModel.Game.getValue().getHoleIds().size())
       // {
        //    LoadHoleScores(viewModel.Game.getValue().getIdKey(), nextHole);
        //}
    }

    public void onPrevHoleClick(View view)
    {
        int prevHole = scoreGameActivViewModel.getPrevHoleNumber();
        saveCurrentHoles(prevHole);

        // Load new holes until next hole is the last one
       //if (prevHole > 0)
       // {
        //    LoadHoleScores(viewModel.Game.getValue().getIdKey(), prevHole);
       // }

    }

    private void saveCurrentHoles(int nextHole)
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();

        if (fragment != null)
        {
            if ( scoreGameActivViewModel.CurrentUserIsCreator(FrolfApp.getUserSession().getCurrentUserId() ) )
            {
                scoreGameActivViewModel.SaveHoleScores(fragment.getHoleScores(), new IApiResponseListener()
                {
                    @Override
                    public void onResponseSuccessful()
                    {
                        loadNextHole(nextHole);
                    }

                    @Override
                    public void onResponseFailed()
                    {
                        // todo: toast message
                    }

                    @Override
                    public void onCallFailure()
                    {
                        // todo: toast message
                    }
                });
            }
            else
            {
                loadNextHole(nextHole);
            }
        }
    }

    private void loadNextHole(int nextHole)
    {
        if ( withinHoleBoundary(nextHole) )
        {
            LoadHoleScores(scoreGameActivViewModel.Game.getValue().getIdKey(), nextHole);
        }
    }

    private boolean withinHoleBoundary(int n)
    {
        return n > 0 && n <= scoreGameActivViewModel.Game.getValue().getHoleIds().size();
    }

    private GameHoleScoresFragment getGameHoleScoreFrag()
    {
        return (GameHoleScoresFragment)
            getSupportFragmentManager().findFragmentById(R.id.frag_mng_game_hole_scores_fragment);
    }
}
