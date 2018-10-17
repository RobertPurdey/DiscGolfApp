package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitBuilder;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IRetrofitBuilder;
import robert.purdey.caddytracker.ui.models.LoginModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public class LoginViewModel extends ViewModel
{
    private boolean isSuccessfulLogin = false;

    public void login(String username, String password)
    {
        IRetrofitBuilder retrofitBuilder = new RetrofitBuilder(
            new RetrofitConfig(),
            new HttpClientConfig()
        );
        HashMap<String, String> clientHeaders = new HashMap<String, String>();
        clientHeaders.put(
            "Content-Type",
            "application/x-www-form-urlencoded"
        );

        HttpClientArg clientArg = new HttpClientArg(
            10,
            10,
            IApiCall.BASE_URL,
            clientHeaders);

        LoginModel loginAttempt = new LoginModel(username, password);

        Retrofit retrofit            = retrofitBuilder.build(clientArg);
        IApiCall call                = retrofit.create(IApiCall.class);
        Call<TokenModel> caller      = call.login(loginAttempt);

        caller.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response)
            {
                if ( response.isSuccessful() )
                {
                    isSuccessfulLogin = true;
                    //todo: store login session
                }
                else
                {
                    isSuccessfulLogin = false;
                }

            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t)
            {
                System.out.println("Failed to login");
            }
        });
    }
}
