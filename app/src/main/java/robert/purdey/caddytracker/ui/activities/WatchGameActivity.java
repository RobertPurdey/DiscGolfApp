package robert.purdey.caddytracker.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.FrolfApp;


public class WatchGameActivity extends AppCompatActivity
{
    TestClass ts;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_results);

        // Attempt to get id.
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(ScoreGameActivity.RECORD_ID);

        // todo: throw error if one isnt given? cant score no game :D

        // get record data when set
        if ( !recordId.equals("") )
        {
            ts = new TestClass(UUID.fromString(recordId));
        }
    }


    // todo Move somewhere better duh
    class TestClass
    {
        private String serverIpAddress = "192.168.1.101";
        private UUID gameId;

        public String results="";

        public TestClass(UUID gameId)
        {
            Thread cThread  = new Thread(new ClientThread());
            this.gameId     = gameId;

            cThread.start();
        }


        public class ClientThread implements Runnable
        {
            public void run()
            {
                try
                {
                    InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
                    Log.d("ClientActivity", "C: Connecting...");

                    results="";
                    try
                    {
                        String s            = null;
                        Socket socket       = new Socket(serverAddr, 45000);
                        BufferedReader in   = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter out  = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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
                        out.write("--SPECTATE");
                        out.flush();

                        // Sending game guid as string (36 bytes)
                        // todo: use real game guid
                        out.write(gameId.toString());
                        out.flush();

                        // Sending user guid as string (36 bytes)
                        // todo: use real user guid (not katie/katie)
                        out.write(FrolfApp.getUserSession().getCurrentUserId().toString());
                        out.flush();

                        socket.close();
                        Log.d("ClientActivity", "C: Closed.");
                    } catch (Exception e){
                        Log.e("ClientActivity", "S: Error", e);
                    }
                }
                catch (Exception e) { Log.e("ClientActivity", "C: Error", e);}
            }
        }
    }
}
