package robert.purdey.caddytracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.UUID;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.networking.watch.SpectatorClient;
import robert.purdey.caddytracker.ui.callbacks.IGameUpdateCallback;
import robert.purdey.caddytracker.ui.fragments.ScoreCardFragment;
import robert.purdey.caddytracker.ui.models.GameResultModel;


public class WatchGameActivity extends AppCompatActivity implements IGameUpdateCallback
{
    public static final String RECORD_ID = "WATCH_GAME_RECORD_ID";
    SpectatorClient spectator;
    ScoreCardFragment scoreCardFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_game);

        // Attempt to get id.
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(WatchGameActivity.RECORD_ID);

        // todo: throw error if one isnt given? cant score no game :D

        // setup spectator client
        if ( !recordId.equals("") )
        {
            spectator = new SpectatorClient(UUID.fromString(recordId), this);
        }
    }

    public void LoadGameUpdate(GameResultModel gameUpdate)
    {
        final GameResultModel gameUpdateResult = gameUpdate;

        runOnUiThread(() -> LoadGameUpdateFrag(gameUpdateResult));
    }

    public void LoadGameUpdateFrag(GameResultModel gameUpdate)
    {
        scoreCardFrag = (ScoreCardFragment)
            getSupportFragmentManager().findFragmentById(R.id.frag_watch_game_score_card_update_game);

        scoreCardFrag.LoadScoreCard(gameUpdate);
    }
}
