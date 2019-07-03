package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;

import robert.purdey.caddytracker.databinding.ActivityScoreGameBinding;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.fragments.GameHoleScoresFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.helpers.Toaster;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameModel;
import robert.purdey.caddytracker.ui.models.GameResultModel;
import robert.purdey.caddytracker.ui.viewmodels.ScoreGameActivityViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

public class ScoreGameActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    private ScoreGameActivityViewModel scoreGameActiveViewModel;
    Announcer announce;

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
                TextView courseName = findViewById(R.id.txtv_activity_score_course_name);
                courseName.setText(gameModel.getCourseName());
                LoadHoleScores(gameModel.getIdKey(), 1);

                announce = new Announcer(gameModel.getIdKey());
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
            gameResultModel -> announce.SendGameUpdate(gameResultModel));
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

    // todo Move somewhere better
    class Announcer
    {
        // todo: read from file
        private String serverIpAddress = "192.168.1.101";
        private UUID gameId;

        public String results="";

        String s;
        Socket socket;
        BufferedReader in;
        BufferedWriter out;

        public Announcer(UUID gameId)
        {
            Thread cThread  = new Thread(new ScoreGameActivity.Announcer.MakeConnection());
            this.gameId     = gameId;

            cThread.start();
        }

        public void SendGameUpdate(GameResultModel gameUpdate)
        {
            // convert to JSON
            Gson gson               = new Gson();
            String updateStr        = gson.toJson(gameUpdate);

            // todo: dont let this be called if not established
            Thread cThread  = new Thread(new ScoreGameActivity.Announcer.GameUpdate(updateStr));
            cThread.start();
        }

        private void packageGameUpdate(GameModel model)
        {

        }

        public void SendGameUpdate(String gameUpdate)
        {
            // todo: dont let this be called if not established
            Thread cThread  = new Thread(new ScoreGameActivity.Announcer.GameUpdate(gameUpdate));
            cThread.start();
        }


        private class GameUpdate implements Runnable
        {
            private String gameUpdate = "";

            public GameUpdate(String newGameUpdate)
            {
                gameUpdate = newGameUpdate;
            }

            @Override
            public void run()
            {
                try
                {
                    int updateSize         = gameUpdate.length();
                    String updateSizeMsg   = String.format("%07d" , updateSize);

                    // send update size
                    out.write(updateSizeMsg);
                    out.flush();

                    // send update
                    out.write(gameUpdate);
                    out.flush();

                    Log.d("GameUpdate", "C: Update sent...");
                }
                catch (Exception ex)
                {
                    Log.d("GameUpdate", "C: Update not sent...");
                }
            }
        }

        private class MakeConnection implements Runnable
        {
            @Override
            public void run()
            {
                try
                {
                    InetAddress serverAddr = InetAddress.getByName(serverIpAddress);

                    s       = null;
                    socket  = new Socket(serverAddr, 45000);
                    in      = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out     = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    Log.d("Announcer", "C: Connected to broadcast server :) ...");
                }
                catch (Exception ex)
                {
                    Log.d("Announcer", "C: Failure to connect to broadcast server :( ...");
                }

                EstablishConnection();
            }

            private void EstablishConnection()
            {
                try
                {
                    // Sending token size + token
                    String token            = FrolfApp.getUserSession().getToken();
                    byte[] tokenByte        = token.getBytes();
                    int tokenSize           = tokenByte.length;
                    String tokenSizeMsg     = String.format("%04d" , tokenSize);

                    // send token size
                    out.write(tokenSizeMsg);
                    out.flush();

                    // send token
                    out.write(token);
                    out.flush();

                    // Sending command spectate command is always 10 chars (10 bytes)
                    out.write("--ANNOUNCE");
                    out.flush();

                    // Sending game guid as string (36 bytes)
                    out.write(gameId.toString());
                    out.flush();

                    // Sending user guid as string (36 bytes)
                    out.write(FrolfApp.getUserSession().getCurrentUserId().toString());
                    out.flush();

                    Log.d("Announcer", "C: Connection established :) ...");
                }
                catch (Exception ex)
                {
                    Log.d("Announcer", "C: Failure to establish connection :( ...");
                }
            }
        }
    }
}

