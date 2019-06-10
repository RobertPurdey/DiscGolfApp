package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityAccountBinding;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.viewmodels.AccountViewModel;

public class AccountActivity extends AppCompatActivity
{
    private AccountViewModel accountViewModel;

    public AccountActivity()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setAccountViewModel();
        ActivityAccountBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_account);

        binding.setAccountViewModel(accountViewModel);
        binding.setLifecycleOwner(this);

        accountViewModel.getAccountInfo(new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                // todo:
            }

            @Override
            public void onResponseFailed()
            {
                // todo:
            }

            @Override
            public void onCallFailure()
            {
                // todo:
            }
        }).observe(this, appUserModel -> {
            accountViewModel.setAccountInfo(appUserModel);
            unfocusFriendCodeText();
        });
    }

    private void unfocusFriendCodeText()
    {
        EditText txtFriendCode = findViewById(R.id.etxt_activity_account_friend_code);
        txtFriendCode.setFocusable(false);
    }

    private void setAccountViewModel()
    {
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
    }

    public void onSave(View view)
    {
        accountViewModel.updateAccount(new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                // todo
            }

            @Override
            public void onResponseFailed()
            {
                // todo
            }

            @Override
            public void onCallFailure()
            {
                // todo
            }
        });
    }

}
