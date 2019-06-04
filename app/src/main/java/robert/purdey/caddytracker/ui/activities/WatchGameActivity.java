package robert.purdey.caddytracker.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.fragments.ScoreCardFragment;
import robert.purdey.caddytracker.ui.models.GameResultModel;


public class WatchGameActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "WATCH_GAME_RECORD_ID";
    Spectator spectator;
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

        // get record data when set
        if ( !recordId.equals("") )
        {
            spectator = new Spectator(UUID.fromString(recordId));
        }
    }

    public void LoadGameUpdate(GameResultModel gameUpdate) {
        final GameResultModel gameUpdateResult = gameUpdate;

        runOnUiThread(() -> LoadGameUpdateFrag(gameUpdateResult));
    }
    public void LoadGameUpdateFrag(GameResultModel gameUpdate)
    {
        scoreCardFrag = (ScoreCardFragment)
            getSupportFragmentManager().findFragmentById(R.id.frag_watch_game_score_card_update_game);

        scoreCardFrag.LoadScoreCard(gameUpdate);
    }

    // todo Move somewhere better
    class Spectator
    {
        // todo: read from file
        private String serverIpAddress = "192.168.1.101";
        private UUID gameId;

        public String results="";

        String s;
        Socket socket;
        BufferedReader in;
        BufferedWriter out;

        public Spectator(UUID gameId)
        {
            Thread cThread  = new Thread(new WatchGameActivity.Spectator.MakeConnection());
            this.gameId     = gameId;

            cThread.start();

            ReceiveGameUpdates();
        }

        public void ReceiveGameUpdates()
        {
            // todo: dont let this be called if not established
            Thread cThread  = new Thread(new WatchGameActivity.Spectator.GameUpdate());
            cThread.start();
        }


        private class GameUpdate implements Runnable
        {
            public GameUpdate()
            {

            }

            @Override
            public void run()
            {
                char[] receivedMsg = new char[10];

                while (String.copyValueOf(receivedMsg) != "-ENDOFGAME")
                {
                    try
                    {
                        String gameUpdate           = receiveGameUpdate();
                        Gson gson                   = new Gson();
                        GameResultModel updateModel = gson.fromJson(gameUpdate, GameResultModel.class);

                        LoadGameUpdate(updateModel);
                        Log.d("GameUpdate", "C: Game update received: " + gameUpdate);
                    }
                    catch (Exception ex)
                    {
                        Log.d("GameUpdate", "C: Update not sent...");
                    }
                }
            }

            private String receiveGameUpdate()
            {
                char[] updateSize   = new char[7];
                int charsRead       = 0;
                int readCount;

                while (charsRead < 7)
                {
                    // read game update size
                    try
                    {
                        readCount = in.read(updateSize, charsRead, 7);

                        if (readCount != -1)
                            charsRead += readCount;
                    }
                    catch (Exception ex)
                    {
                        Log.d("Spectator", "C: Failed to receive game update size...");
                    }
                }

                // update size to int
                String updateSizeStr    = String.copyValueOf(updateSize);
                int    updateSizeInt    = Integer.parseInt(updateSizeStr);
                char[] gameUpdate       = new char[updateSizeInt];
                readCount               = 0;
                charsRead               = 0;

                while (charsRead < updateSizeInt)
                {
                    // read game update
                    try
                    {
                        readCount = in.read(gameUpdate, charsRead, updateSizeInt);

                        if (readCount != -1)
                            charsRead += readCount;
                    }
                    catch (Exception ex)
                    {
                        Log.d("Spectator", "C: Failed to receive game update size...");
                    }
                }

                return String.copyValueOf(gameUpdate);
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
                    int tokenSize           = token.length();
                    String tokenSizeMsg     = String.format("%04d" , tokenSize);

                    // send token size
                    out.write(tokenSizeMsg);
                    out.flush();

                    // send token
                    out.write(token);
                    out.flush();

                    // Sending command spectate command is always 10 chars (10 bytes)
                    out.write("--SPECTATE");
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
