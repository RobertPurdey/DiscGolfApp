package robert.purdey.caddytracker.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityLoginBinding;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.helpers.Toaster;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.TokenModel;
import robert.purdey.caddytracker.ui.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity
{
    // todo: what to do with this??
    private TokenModel receivedLoginTokenModel;
    private LoginViewModel loginViewModel;
    private boolean isWaiting;

    public LoginActivity()
    {
        isWaiting = false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        createLoginViewModel();

        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        isWaiting = false;

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
        if ( !isWaiting )
        {
            isWaiting = true;

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
                                    isWaiting = false;
                                    ActivityStarter.startMainMenuActivity(LoginActivity.this);
                                }

                                @Override
                                public void onResponseFailed()
                                {
                                    isWaiting = false;
                                }

                                @Override
                                public void onCallFailure()
                                {
                                    isWaiting = false;
                                }
                            });
                        }

                        @Override
                        public void onResponseFailed()
                        {
                            isWaiting = false;
                        }

                        @Override
                        public void onCallFailure()
                        {
                            isWaiting =false;
                        }
                    });
                }

                @Override
                public void onResponseFailed()
                {
                    loginFailedToastShow();
                    isWaiting = false;
                }

                @Override
                public void onCallFailure()
                {
                    loginFailedToastShow();
                    isWaiting = false;
                }
            });
        }
    }

    private void loginFailedToastShow()
    {
        Toaster.quickFailureToast(
            LoginActivity.this,
            R.string.login_failed_msg,
            Toast.LENGTH_LONG);
    }



    public void onCreateAccount(View view)
    {
        ActivityStarter.startCreateAccountActivity(this);
    }

    private void createLoginViewModel()
    {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }
}
