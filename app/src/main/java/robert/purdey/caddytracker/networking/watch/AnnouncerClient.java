package robert.purdey.caddytracker.networking.watch;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonSerializer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;

import robert.purdey.caddytracker.domain.clients.ClientRequestModel;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.networking.encryption.ModelEncryptor;
import robert.purdey.caddytracker.security.encryption.AesManager;
import robert.purdey.caddytracker.security.encryption.RsaManager;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.models.GameResultModel;

public class AnnouncerClient
{
    private ModelEncryptor encryptor;
    private Gson gson = new Gson();
    private String serverIpAddress = "192.168.1.86";
    private UUID gameId;

    public String results="";

    String s;
    Socket socket;
    BufferedReader in;
    BufferedWriter out;

    public AnnouncerClient(UUID gameId)
    {
        Thread cThread  = new Thread(new AnnouncerClient.MakeConnection());
        this.gameId     = gameId;

        try
        {
            encryptor = new ModelEncryptor(new RsaManager(), new AesManager());
        }
        catch (Exception ex) { }

        cThread.start();
    }

    public void SendGameUpdate(GameResultModel gameUpdate)
    {
        EncryptModel encryptGame = encryptor.encrypt(gameUpdate);

        String updateStr     = gson.toJson(encryptGame);
        Thread cThread       = new Thread(new AnnouncerClient.GameUpdate(updateStr));
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
                String updateSizeMsg   = String.format("%08d" , updateSize);

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
                // Creating announce client request
                String token = FrolfApp.getUserSession().getToken();

                ClientRequestModel request   = new ClientRequestModel(token, "ANNOUNCE", gameId);
                EncryptModel encrytedRequest = encryptor.encrypt(request);
                String encrytedRequestJson   = gson.toJson(encrytedRequest);

                // Sending request size + request
                byte[] requestBytes     = encrytedRequestJson.getBytes();
                int tokenSize           = requestBytes.length;
                String requestSizeMsg   = String.format("%04d" , tokenSize);

                // send request size
                out.write(requestSizeMsg);
                out.flush();

                // send request
                out.write(encrytedRequestJson);
                out.flush();

                Log.d("Announcer", "C: Connection attempting to establish :) ...");
            }
            catch (Exception ex)
            {
                Log.d("Announcer", "C: Failure to establish connection :( ...");
            }
        }
    }
}
