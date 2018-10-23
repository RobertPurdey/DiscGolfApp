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
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.controllers.AppUserController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.LoginModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> username;
    public MutableLiveData<String> password;
    public MutableLiveData<TokenModel> receivedToken;

    private IAppUserController appUserController;
    private LoginRequestListener loginListener;

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

        appUserController = new AppUserController(apiCallService);
    }


    public void login()
    {
        LoginModel loginAttempt = new LoginModel(username.getValue(), password.getValue());

        receivedToken = appUserController.login(
            loginListener,
            loginAttempt.getRequestFields());
    }

    public void setLoginListener(LoginRequestListener loginListener)
    {
        this.loginListener = loginListener;
    }
}
