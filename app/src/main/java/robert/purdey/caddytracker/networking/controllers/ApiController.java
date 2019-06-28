package robert.purdey.caddytracker.networking.controllers;

import com.google.gson.Gson;

import okhttp3.Authenticator;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.authentication.TokenRefresher;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IApiController;
import robert.purdey.caddytracker.networking.contracts.encryption.IModelEncryptor;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.networking.encryption.ModelEncryptor;
import robert.purdey.caddytracker.security.encryption.AesManager;
import robert.purdey.caddytracker.security.encryption.RsaManager;
import robert.purdey.caddytracker.ui.FrolfApp;


public abstract class ApiController<TApiCall> implements IApiController<TApiCall>
{
    private TApiCall apiCall;
    private IApiCallService apiCallService;
    private IModelEncryptor modelEncryptor;
    private HttpClientArg httpClientArg;
    private Class<TApiCall> apiCallClass;

    public ApiController(
        IApiCallService apiCallService,
        Class<TApiCall> apiCallClass)
    {
        this.apiCallService  = apiCallService;
        this.apiCallClass    = apiCallClass;
        this.modelEncryptor  = createModelEncryptor();
        this.httpClientArg   = createHttpClientArg();
        this.apiCall         = apiCallService.getApiCall(httpClientArg, apiCallClass);
    }

    private HttpClientArg createHttpClientArg()
    {
        return new HttpClientArg(
            10,
            10,
            IApiCall.BASE_URL,
            CreateAuthenticator() );
    }

    private IModelEncryptor createModelEncryptor()
    {
        IModelEncryptor modelEncryptor = null;

        try
        {
            RsaManager rsaManager   = new RsaManager();
            AesManager aesManager   = new AesManager();

            modelEncryptor = new ModelEncryptor(rsaManager, aesManager);
        }
        catch (Exception ex)
        {

        }

        return modelEncryptor;
    }

    protected String getAuthorizationHeader()
    {
        return "Bearer " + getToken();
    }

    protected String getToken()
    {
        return FrolfApp.getUserSession().getToken();
    }

    protected TApiCall getApiCall()
    {
        return apiCall;
    }

    protected HttpClientArg getHttpClientArg()
    {
        return httpClientArg;
    }

    protected <T> EncryptModel encryptModel(T model)
    {
        return modelEncryptor.encrypt(model);
    }

    protected <T> T decryptModel(EncryptModel model, Class<T> tClass)
    {
        return modelEncryptor.decrypt(model, tClass);
    }

    private Authenticator CreateAuthenticator()
    {
        return new TokenRefresher(apiCallService);
    }
}
