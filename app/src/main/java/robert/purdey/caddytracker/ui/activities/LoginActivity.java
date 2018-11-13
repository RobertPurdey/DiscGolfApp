package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityLoginBinding;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
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
                ActivityStarter.startMainMenuActivity(LoginActivity.this);
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

    private void createLoginViewModel()
    {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }
}
