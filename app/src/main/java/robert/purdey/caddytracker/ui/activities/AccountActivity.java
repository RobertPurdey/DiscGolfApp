package robert.purdey.caddytracker.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityAccountBinding;
import robert.purdey.caddytracker.ui.helpers.Toaster;
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

            }

            @Override
            public void onResponseFailed()
            {

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

    private void updateAccountSuccessToast()
    {
        Toaster.quickSuccessToast(
            AccountActivity.this,
            R.string.update_account_success_msg,
            Toast.LENGTH_SHORT);
    }

    private void updateAccountFailureToast()
    {
        Toaster.quickFailureToast(
            AccountActivity.this,
            R.string.update_account_failure_msg,
            Toast.LENGTH_SHORT);
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
                updateAccountSuccessToast();
            }

            @Override
            public void onResponseFailed()
            {
                updateAccountFailureToast();
            }

            @Override
            public void onCallFailure()
            {
                // todo
            }
        });
    }

    public void onShareFriendCode(View view)
    {
        Intent shareIntent = new Intent();

        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, accountViewModel.friendCode.getValue());
        shareIntent.setType("text/plain");

        startActivity(shareIntent);
    }

}
