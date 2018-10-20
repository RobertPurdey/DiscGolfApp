package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityLoginBinding;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.models.TokenModel;
import robert.purdey.caddytracker.ui.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity
{
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

        createLoginViewModel(this);

        ActivityLoginBinding binding  = DataBindingUtil.setContentView(this, R.layout.activity_login);
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
        loginViewModel.login();
        // todo: login user API call
    }

    private void createLoginViewModel(Context context)
    {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.setLoginListener(new LoginViewModel.LoginRequestListener()
        {
            @Override
            public void onLoginSuccessful()
            {
                ActivityStarter.startMainMenuActivity(context);
            }

            @Override
            public void onLoginFailed()
            {
                Toast.makeText(context, "Failed to login. The username and/or password may be incorrect.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCallFailed()
            {
                Toast.makeText(context, "Failed to login. The username and/or password may be incorrect.", Toast.LENGTH_LONG);
            }
        });
    }
}
