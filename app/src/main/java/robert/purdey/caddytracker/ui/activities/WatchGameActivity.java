package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.OperationApplicationException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zsoft.signala.hubs.HubConnection;
import com.zsoft.signala.hubs.HubOnDataCallback;
import com.zsoft.signala.hubs.IHubProxy;
import com.zsoft.signala.transport.StateBase;
import com.zsoft.signala.transport.longpolling.LongPollingTransport;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.viewmodels.CourseListViewModel;

public class WatchGameActivity extends AppCompatActivity
{
    protected HubConnection con     = null;
    protected IHubProxy hub         = null;
    protected boolean isConnected   = false;
    private CourseListViewModel courseListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_game);
        courseListViewModel = ViewModelProviders.of(this).get(CourseListViewModel.class);
    }

    public void onApi(View view)
    {
        courseListViewModel.getCourses();
    }

    public void onWatch(View view)
    {
        testClass t = new testClass();
//        con = new HubConnection(IApiCall.BASE_URL, this, new LongPollingTransport())
//        {
//            @Override
//            public void OnStateChanged(StateBase oldState, StateBase newState) {
//
//                switch(newState.getState())
//                {
//                    case Connected:
//                        isConnected = true;
//                        break;
//                    case Disconnected:
//                        isConnected = false;
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void OnError(Exception exception) {
//                Toast.makeText(WatchGameActivity.this, "On error: " + exception.getMessage(), Toast.LENGTH_LONG).show();
//            }
//
//        };
//
//        try {
//            hub = con.CreateHubProxy("watchGame");
//        } catch (OperationApplicationException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        hub.On("hello", new HubOnDataCallback()
//        {
//            @Override
//            public void OnReceived(JSONArray args) {
//                Toast.makeText(WatchGameActivity.this, "Holy fuck", Toast.LENGTH_LONG).show();
//            }
//        });
//
//
//        con.Start();
    }

    class testClass
    {
        private String serverIpAddress = "192.168.1.66";
        public String results="";
        public testClass()
        {
            Thread cThread = new Thread(new   ClientThread());
            cThread.start();
        }


        public class ClientThread implements Runnable
        {
            public void run()
            {
                try
                {
                    InetAddress serverAddr = InetAddress.getByName("192.168.1.66");
                    Log.d("ClientActivity", "C: Connecting...");

                    results="";
                    try
                    {
                        String s = null;
                        Socket socket = new Socket(serverAddr, 45000);
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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
