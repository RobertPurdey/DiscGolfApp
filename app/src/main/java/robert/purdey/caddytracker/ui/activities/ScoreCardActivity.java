package robert.purdey.caddytracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.ScoreCardFragment;
import robert.purdey.caddytracker.ui.models.GameResultModel;
import robert.purdey.caddytracker.ui.viewmodels.ScoreCardViewModel;

public class ScoreCardActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    private ScoreCardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_card);

        // Attempt to get id.
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(ScoreGameActivity.RECORD_ID);
        viewModel       = ViewModelProviders.of(this).get(ScoreCardViewModel.class);

        // todo: throw error if one isnt given? cant score no game :D

        // get record data when set
        if ( !recordId.equals("") )
        {
            viewModel.getGameResults(UUID.fromString(recordId)).observe(this, gameResultModel ->
                LoadScoreCard(gameResultModel)
            );
        }
    }

    private void LoadScoreCard(GameResultModel result)
    {
        ScoreCardFragment scoreCard = getGameHoleScoreFrag();
        scoreCard.LoadScoreCard(result);
    }

    private ScoreCardFragment getGameHoleScoreFrag()
    {
        return (ScoreCardFragment)
            getSupportFragmentManager().findFragmentById(R.id.frag_score_card_game_results);
    }
}
