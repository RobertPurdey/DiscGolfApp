package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;

import robert.purdey.caddytracker.databinding.ActivityScoreGameBinding;
import robert.purdey.caddytracker.networking.watch.AnnouncerClient;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.fragments.GameHoleScoresFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.helpers.Toaster;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.viewmodels.ScoreGameActivityViewModel;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import java.util.UUID;

public class ScoreGameActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";

    private ScoreGameActivityViewModel scoreGameActiveViewModel;
    private AnnouncerClient announcer;

    public ScoreGameActivity()
    {

    }

    // todo: look at frolfGroupRecord for more ideas on how to bind
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        ActivityScoreGameBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_score_game);
        scoreGameActiveViewModel         = ViewModelProviders.of(this).get(ScoreGameActivityViewModel.class);

        binding.setScoreGameActivViewModel(scoreGameActiveViewModel);
        binding.setLifecycleOwner(this);

        // Attempt to get id.
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(ScoreGameActivity.RECORD_ID);

        // todo: throw error if one isnt given? cant score no game :D

        // get record data when set
        if ( !recordId.equals("") )
        {
            scoreGameActiveViewModel.getGame(UUID.fromString(recordId)).observe(this, gameModel -> {
                TextView courseName = findViewById(R.id.txtv_activity_score_game_course_name);
                courseName.setText(gameModel.getCourseName());
                LoadHoleScores(gameModel.getIdKey(), 1);

                announcer = new AnnouncerClient(gameModel.getIdKey());
            });
        }
    }

    private void LoadHoleScores(UUID gameId, int holeNumber)
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();
        scoreGameActiveViewModel.setCurrentHole(holeNumber);
        fragment.Load(gameId, holeNumber);
    }

    public void onNextHoleClick(View view)
    {
        int nextHole = scoreGameActiveViewModel.getNextHoleNumber();

        if ( isLastHole(nextHole - 1) )
        {
            confirmCompleteGame();
        }
        else
        {
            saveCurrentHoles(nextHole);
        }
    }

    public void onPrevHoleClick(View view)
    {
        int prevHole = scoreGameActiveViewModel.getPrevHoleNumber();
        saveCurrentHoles(prevHole);
    }

    private void startScoreCardActivity()
    {
        ActivityStarter.startScoreCardActivity(
            ScoreGameActivity.this,
            scoreGameActiveViewModel.getGameId());
    }

    private void confirmCompleteGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
            .setMessage(R.string.complete_game_confirm)
            .setPositiveButton(R.string.yes, (dialog, id) -> completeGame())
            .setNegativeButton(R.string.no,  (dialog, id) -> dialog.cancel() )
            .show();
    }

    private void completeGame()
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();

        if (fragment != null)
        {
            // todo: only trigger save when no changes were made
            if ( scoreGameActiveViewModel.CurrentUserIsCreator(FrolfApp.getUserSession().getCurrentUserId() ) )
            {
                scoreGameActiveViewModel.SaveHoleScores(fragment.getHoleScores(), new IApiResponseListener()
                {
                    @Override
                    public void onResponseSuccessful()
                    {
                        scoreGameActiveViewModel.completeGame(new IApiResponseListener()
                        {
                            @Override
                            public void onResponseSuccessful()
                            {
                                completeGameSuccessToast();
                                startScoreCardActivity();
                            }

                            @Override
                            public void onResponseFailed()
                            {
                                completeGameFailureToast();
                            }

                            @Override
                            public void onCallFailure()
                            {
                                completeGameFailureToast();
                            }
                        });
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
        }
    }

    private void completeGameSuccessToast()
    {
        Toaster.quickSuccessToast(
            ScoreGameActivity.this,
            R.string.game_complete_success_msg,
            Toast.LENGTH_SHORT);
    }

    private void completeGameFailureToast()
    {
        Toaster.quickFailureToast(
            ScoreGameActivity.this,
            R.string.game_complete_failure_msg,
            Toast.LENGTH_SHORT);
    }

    private void saveCurrentHoles(int nextHole)
    {
        GameHoleScoresFragment fragment = getGameHoleScoreFrag();

        if (fragment != null)
        {
            // todo: only trigger save when no changes were made
            if ( scoreGameActiveViewModel.CurrentUserIsCreator(FrolfApp.getUserSession().getCurrentUserId() ) )
            {
                scoreGameActiveViewModel.SaveHoleScores(fragment.getHoleScores(), new IApiResponseListener()
                {
                    @Override
                    public void onResponseSuccessful()
                    {
                        loadNextHole(nextHole);
                        triggerGameUpdate();
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

    private void triggerGameUpdate()
    {

        // send new data to socket
        // todo: send real game data
        // get game update, send
        scoreGameActiveViewModel.getGameResults(scoreGameActiveViewModel.Game.getValue().getIdKey()).observe(
            this,
            gameResultModel -> announcer.SendGameUpdate(gameResultModel));
    }

    private void loadNextHole(int nextHole)
    {
        if ( withinHoleBoundary(nextHole) )
        {
            LoadHoleScores(scoreGameActiveViewModel.Game.getValue().getIdKey(), nextHole);
        }
    }

    private boolean withinHoleBoundary(int n)
    {
        return n > 0 && n <= scoreGameActiveViewModel.Game.getValue().getHoleIds().size();
    }

    private boolean isLastHole(int n)
    {
        return n == scoreGameActiveViewModel.Game.getValue().getHoleIds().size();
    }

    private GameHoleScoresFragment getGameHoleScoreFrag()
    {
        return (GameHoleScoresFragment)
            getSupportFragmentManager().findFragmentById(R.id.frag_mng_game_hole_scores_fragment);
    }
}

