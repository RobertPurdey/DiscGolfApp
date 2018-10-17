package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.HashMap;
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

    public MutableLiveData<String> username;
    public MutableLiveData<String> password;

    public MutableLiveData<TokenModel> receivedToken;

    public LoginViewModel()
    {
        username = new MutableLiveData<>();
        password = new MutableLiveData<>();
    }


    public void login()
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

        LoginModel loginAttempt = new LoginModel(username.getValue(), password.getValue());

        Retrofit retrofit            = retrofitBuilder.build(clientArg);
        IApiCall call                = retrofit.create(IApiCall.class);
        Call<TokenModel> caller      = call.login(loginAttempt);

        caller.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response)
            {
                if ( response.isSuccessful() )
                {
                    receivedToken.setValue(response.body());
                    isSuccessfulLogin  = true;
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

    public MutableLiveData<String> getUsername()
    {
        return username;
    }

    public void setUsername(MutableLiveData<String> username)
    {
        this.username = username;
    }

    public MutableLiveData<String> getPassword()
    {
        return password;
    }

    public void setPassword(MutableLiveData<String> password)
    {
        this.password = password;
    }
}
