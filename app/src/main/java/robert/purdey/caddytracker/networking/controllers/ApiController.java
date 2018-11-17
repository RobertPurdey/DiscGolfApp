package robert.purdey.caddytracker.networking.controllers;

import okhttp3.Authenticator;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.authentication.TokenRefresher;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IApiController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.FrolfApp;


public abstract class ApiController<TApiCall> implements IApiController<TApiCall>
{
    private TApiCall apiCall;
    private IApiCallService apiCallService;
    private HttpClientArg httpClientArg;
    private Class<TApiCall> apiCallClass;

    public ApiController(
        IApiCallService apiCallService,
        Class<TApiCall> apiCallClass)
    {
        this.apiCallService  = apiCallService;
        this.apiCallClass    = apiCallClass;
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

    private Authenticator CreateAuthenticator()
    {
        return new TokenRefresher(apiCallService);
    }
}
