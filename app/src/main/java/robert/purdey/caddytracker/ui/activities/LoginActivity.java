package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Arrays;
import java.util.Base64;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityLoginBinding;
import robert.purdey.caddytracker.security.contracts.IRsaManager;
import robert.purdey.caddytracker.security.encryption.RsaManager;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.TokenModel;
import robert.purdey.caddytracker.ui.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity
{
    // todo: what to do with this??
    private TokenModel receivedLoginTokenModel;
    private LoginViewModel loginViewModel;

    public LoginActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        createLoginViewModel();

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
    }

    /**
     * Logs the user into the application
     *
     * @param view - view calling the method
     */
    public void onLoginUser(View view)
    {
        loginViewModel.login(new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                loginViewModel.setRsaKeys(new IApiResponseListener()
                {
                    @Override
                    public void onResponseSuccessful()
                    {
                        loginViewModel.storeCurrentUserInfo(new IApiResponseListener()
                        {
                            @Override
                            public void onResponseSuccessful()
                            {
                                ActivityStarter.startMainMenuActivity(LoginActivity.this);
                            }

                            @Override
                            public void onResponseFailed()
                            {
                                // wont happen
                            }

                            @Override
                            public void onCallFailure()
                            {
                                // wont happen
                            }
                        });
                    }

                    @Override
                    public void onResponseFailed()
                    {

                    }

                    @Override
                    public void onCallFailure()
                    {

                    }
                });
            }

            @Override
            public void onResponseFailed()
            {
                Toast.makeText(
                    LoginActivity.this,
                    "Failed to login. The username and/or password may be incorrect.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCallFailure()
            {
                Toast.makeText(
                    LoginActivity.this,
                    "Failed to login. The username and/or password may be incorrect.", Toast.LENGTH_LONG);
            }
        });
    }

    public void onCreateAccount(View view)
    {
        ActivityStarter.startCreateAccountActivity(this);
    }


    public void onTestEncrypt(View view)
    {
        //try
       // {
            // test encryption
         //   IRsaManager manager  = new RsaManager();
         //   KeyPair genPair      = manager.generateRsaKeyPair();

        //    String testMsg = "test";

            byte[] modulusBytes = Base64.getDecoder().decode("9zZBzXhq2GE2iDhwrjtI4goUARU2d2/R0TGZPiesbn6wOI7uNPePEhd3kaev8sa0Kb79S6oJdrD/0uf7FjUgEB6qtfC/gK3q0HofEFMzyAKyoJSqxWMd3s4bdBFYu9cWttHhNiwK0WbYjJMmUUUkRjkIVhPx6M6cQbKz45bEfl+OZUpC/JMMlUuIgQ4gqecKqdeV+de3Pk+hdTu5YgS0fPAu3WxiNBbFJ9l1rCyNkYDKdIH8GWGXon3Y70MT0P1vai+/VoKx9L2X3L2Dhl31zf1LTK7l1N/WIBYld/InA7ZtqLb/xJdWiNnoMUwFcoNCSCr3PEeMm9gk+g4bbWJfjQ==");
            byte[] exponentBytes = Base64.getDecoder().decode("AQAB");
            byte[] dBytes = Base64.getDecoder().decode("zoPI3LjnqPMs9wcPOr3T2ODKbU0nPwduo+9nMQE7juLOm7DrVdwo7NglzsvitFFCWE1wlDDrzvd1/t5EZvziWBUGTw9bK0gejSI3qQ+YhlGan4MSVerDHUnYrVGAawr3sqoKFZMdRmlAJc8Xh3TXJMKoMCBhSjavWkLK/CkK5PWSRWw2CT6FOQZKjEOjs+i3ajhi3u3sbxnV5CS3IG1he/gS9p0lSVBe/o0t17jUXhw09PtEtT+P4vbl1qDkk6gCn6B8D6MPIcgR+6IR14PWVLud3LG5YylwEq/XMa7Jun/ndW78z3Vp1R7fspXhiDrprEivHSUBTpkIHXLRhEtCkQ==");

         //   RSAPublicKey rsaPublicKey  = (RSAPublicKey)genPair.getPublic();
         //   RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)genPair.getPrivate();

         //   BigInteger modulus = rsaPublicKey.getModulus();
        //    BigInteger pubeExp  = rsaPublicKey.getPublicExponent();
        //    BigInteger priveExp = rsaPrivateKey.getPrivateExponent();

         //   Base64 modulus64  = Base64.getEncoder().
         //   Base64 pubeExp64  =
         //   Base64 priveExp64 =

            BigInteger keyMod = new BigInteger(1, modulusBytes);

            BigInteger pubExp = new BigInteger(1, exponentBytes);

            BigInteger privExp = new BigInteger(1, dBytes);

         //   RSAPublicKeySpec publicKey      = new RSAPublicKeySpec(keyMod, pubExp);
            RSAPrivateKeySpec privateKey    = new RSAPrivateKeySpec(keyMod, privExp);

           // System.out.println("Public Key: " + Arrays.toString(publicKeyBytes));
           // System.out.println("Private Key: " + Arrays.toString(privateKeyBytes));

           // System.out.println("Public Key base64: " + Base64.getMimeEncoder().encodeToString(publicKeyBytes));
           // System.out.println("Private Key base64: " + Base64.getMimeEncoder().encodeToString(privateKeyBytes));

            //RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec();

            //RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec();

         //   System.out.println("Message to encrypt: " + testMsg);

           // byte[] encryptedMsg = manager.encrypt(publicKey, testMsg);

          //  String s = Base64.getEncoder().encodeToString(encryptedMsg);

         //   System.out.println("Encrypted message: " + Arrays.toString(encryptedMsg));
         //   System.out.println("Encrypted message base 64: " + s);

         //   String decryptedMsg = manager.decrypt(privateKey, encryptedMsg);

        ///    System.out.println("Decrypted message: " + decryptedMsg);
       // }
       // catch (Exception ex)
        //{
       //     int x =0;
        //}
    }

    private void createLoginViewModel()
    {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }
}
