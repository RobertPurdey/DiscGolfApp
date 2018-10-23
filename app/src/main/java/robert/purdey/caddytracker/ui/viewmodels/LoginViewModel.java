package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.LoginModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> username;
    public MutableLiveData<String> password;
    public MutableLiveData<TokenModel> receivedToken;

    private LoginRequestListener loginListener;
    private IAppUserCall         call;

    public interface LoginRequestListener
    {
        void onLoginSuccessful();
        void onLoginFailed();
        void onCallFailed();
    }

    public LoginViewModel()
    {
        username       = new MutableLiveData<>();
        password       = new MutableLiveData<>();
        receivedToken  = new MutableLiveData<>();
        loginListener  = null;

        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        HttpClientArg clientArg = new HttpClientArg(
            10,
            10,
            IApiCall.BASE_URL,
            new HashMap<>());

        call = apiCallService.getApiCall(clientArg, IAppUserCall.class);
    }


    public void login()
    {
        LoginModel loginAttempt   = new LoginModel(username.getValue(), password.getValue());
        Call<TokenModel> caller   = call.login(loginAttempt.getRequestFields());

        caller.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response)
            {
                if ( response.isSuccessful() )
                {
                    receivedToken.setValue(response.body());
                    loginListener.onLoginSuccessful();

                    //todo: store login session
                }
                else
                {
                    loginListener.onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t)
            {
                loginListener.onCallFailed();
                System.out.println("Failed to login");
            }
        });
    }

    public void setLoginListener(LoginRequestListener loginListener)
    {
        this.loginListener = loginListener;
    }
}
