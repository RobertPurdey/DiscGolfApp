package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.models.TokenModel;

public class LoginActivity extends AppCompatActivity
{
    private TokenModel receivedLoginToken;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Logs the user into the application
     *
     * @param view - view calling the method
     */
    public void onLoginUser(View view)
    {
        String etxtUsername  = ( findViewById(R.id.etxt_username) ).toString();
        String etxtPassword  = ( findViewById(R.id.etxt_password) ).toString();

        // todo: login user API call
    }
}
