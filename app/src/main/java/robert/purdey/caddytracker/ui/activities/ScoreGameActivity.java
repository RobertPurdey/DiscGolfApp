package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.GameHoleScoresFragment;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.UUID;

public class ScoreGameActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";

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

        // todo: throw error if one isnt given? cant score no game :D

        // get record data when set
        if ( !recordId.equals("") )
        {
            UUID rId = UUID.fromString(recordId);
            // always go to the first hole
            LoadHoleScores(rId, 1);
        }
    }

    private void LoadHoleScores(UUID gameId, int holeNumber)
    {
        GameHoleScoresFragment fragment =
            (GameHoleScoresFragment) getSupportFragmentManager().findFragmentById(R.id.frag_mng_game_hole_scores_fragment);

        fragment.Load(gameId, holeNumber);
    }
}
