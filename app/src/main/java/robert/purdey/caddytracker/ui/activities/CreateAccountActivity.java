package robert.purdey.caddytracker.ui.activities;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityCreateAccountBinding;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.viewmodels.CreateAccountViewModel;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity
{
    private CreateAccountViewModel createAccountViewModel;

    public CreateAccountActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        createCreateAccountViewModel();

        ActivityCreateAccountBinding binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_create_account);

        binding.setCreateAccountVm(createAccountViewModel);
        binding.setLifecycleOwner(this);
    }

    /**
     * Logs the user into the application
     *
     * @param view - view calling the method
     */
    public void onCreateAccount(View view)
    {
        createAccountViewModel.createAccount(new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                ActivityStarter.startLoginAcitvity(CreateAccountActivity.this);
            }

            @Override
            public void onResponseFailed()
            {
                Toast.makeText(
                    CreateAccountActivity.this,
                    "Failed to login. The username and/or password may be incorrect.", Toast.LENGTH_LONG);
            }

            @Override
            public void onCallFailure()
            {
                Toast.makeText(
                    CreateAccountActivity.this,
                    "Failed to login. The username and/or password may be incorrect.", Toast.LENGTH_LONG);
            }
        });
    }

    private void createCreateAccountViewModel()
    {
        createAccountViewModel = ViewModelProviders.of(this).get(CreateAccountViewModel.class);
    }
}
