package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.GameListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ResumeGameActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume_game);
        SetFragmentClick();
    }

    private void SetFragmentClick()
    {
        GameListFragment fragment = (GameListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frag_resume_game_select_fragment);

        fragment.SetGameClickListener( (view, id) ->
            ActivityStarter.startScoreGameActivity(this, id)
        );
    }
}
