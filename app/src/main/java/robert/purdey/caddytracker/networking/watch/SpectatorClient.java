package robert.purdey.caddytracker.networking.watch;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.UUID;
import robert.purdey.caddytracker.domain.clients.ClientRequestModel;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.networking.configuration.ServerConfiguration;
import robert.purdey.caddytracker.networking.encryption.ModelEncryptor;
import robert.purdey.caddytracker.security.encryption.AesManager;
import robert.purdey.caddytracker.security.encryption.RsaManager;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.callbacks.IGameUpdateCallback;
import robert.purdey.caddytracker.ui.models.GameResultModel;

public class SpectatorClient
{
    private ModelEncryptor encryptor;
    private Gson gson = new Gson();
    private String serverIpAddress;
    private UUID gameId;
    private IGameUpdateCallback gameCallback;

    public String results="";

    private String s;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    public SpectatorClient(UUID gameId, IGameUpdateCallback gameCallback)
    {
        serverIpAddress   = ServerConfiguration.BROADCASTER_IP;

        this.gameId       = gameId;
        this.gameCallback = gameCallback;

        try
        {
            encryptor = new ModelEncryptor(new RsaManager(), new AesManager());
        }
        catch (Exception ex) { }

        Thread cThread = new Thread(new SpectatorClient.MakeConnection());
        cThread.start();

        ReceiveGameUpdates();
    }

    public void ReceiveGameUpdates()
    {
        // todo: dont let this be called if not established
        Thread cThread  = new Thread(new SpectatorClient.GameUpdate());
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
                    EncryptModel gameUpdate      = receiveGameUpdate();
                    GameResultModel updateModel  = encryptor.decrypt(gameUpdate, GameResultModel.class);

                    gameCallback.LoadGameUpdate(updateModel);

                    Log.d("GameUpdate", "C: Game update received: " + gameUpdate);
                }
                catch (Exception ex)
                {
                    Log.d("GameUpdate", "C: Update not sent...");
                }
            }
        }

        private EncryptModel receiveGameUpdate()
        {
            char[] updateSize   = new char[8];
            int charsRead       = 0;
            int readCount;

            while (charsRead < 8)
            {
                // read game update size
                try
                {
                    readCount = in.read(updateSize, charsRead, 8);

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

            String gameUpdateEncryptedJson = String.copyValueOf(gameUpdate);

            return gson.fromJson(gameUpdateEncryptedJson, EncryptModel.class);
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

                ClientRequestModel request   = new ClientRequestModel(token, "SPECTATE", gameId);
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
