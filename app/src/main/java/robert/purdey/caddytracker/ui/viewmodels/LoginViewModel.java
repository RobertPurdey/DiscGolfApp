package robert.purdey.caddytracker.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import robert.purdey.caddytracker.domain.storage.contracts.IUserSessionManager;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.controllers.AppUserController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.security.encryption.RsaManager;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.LoginModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public class LoginViewModel extends ViewModel
{
    public MutableLiveData<String> username;
    public MutableLiveData<String> password;
    public MutableLiveData<TokenModel> receivedToken;

    private IAppUserController appUserController;

    public LoginViewModel()
    {
        username       = new MutableLiveData<>();
        password       = new MutableLiveData<>();
        receivedToken  = new MutableLiveData<>();

        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        appUserController = new AppUserController(apiCallService);
    }

    public void login(IApiResponseListener loginListener)
    {
        LoginModel loginAttempt = new LoginModel(username.getValue(), password.getValue());

        receivedToken = appUserController.login(
            loginListener,
            loginAttempt);
    }

    public void storeCurrentUserInfo(IApiResponseListener listener)
    {
        appUserController.getCurrentUserInfo(listener);
    }

    public void setRsaKeys(IApiResponseListener listener)
    {
        RSAPublicKey rsaPubKey   = null;
        RSAPrivateKey rsaPrivKey = null;

        try
        {
            RsaManager rsaManager = new RsaManager();
            KeyPair rsaKeyPair    = rsaManager.generateRsaKeyPair();

            rsaPubKey  = (RSAPublicKey)  rsaKeyPair.getPublic();
            rsaPrivKey = (RSAPrivateKey) rsaKeyPair.getPrivate();

            IUserSessionManager userSession = FrolfApp.getUserSession();

            userSession.storeRsaModulus(rsaPubKey.getModulus());
            userSession.storeRsaPublicExponent(rsaPubKey.getPublicExponent());
            userSession.storeRsaPrivateExponent(rsaPrivKey.getPrivateExponent());

            appUserController.setNewPublicKey(rsaPubKey, listener);
        }
        catch (Exception ex) { }
    }
}
