package robert.purdey.caddytracker.networking.controllers;

import java.util.HashMap;

import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IApiController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;


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

    protected TApiCall GetCustomArgApiCall(HttpClientArg arg)
    {
        return apiCallService.getApiCall(arg, apiCallClass);
    }

    /**
     * Provides additional headers for the API call.
     *
     * @return - Headers to supply the API call.
     */
    protected HashMap<String, String> provideHeaders()
    {
        return new HashMap<>();
    }

    private HttpClientArg createHttpClientArg()
    {
        HashMap<String, String> clientHeaders = new HashMap<>();
        // todo: this needs to come from account manager
        clientHeaders.put(
            "Authorization",
            "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoia2F0aWUiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6ImY5OTY4MDIzLTU4M2MtNDk2Mi1hZTk0LWRjYTMyMjc1NTA2OSIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvcm9sZSI6ImFwcHVzZXIiLCJuYmYiOjE1NDE4Mjk3MjQsImV4cCI6MTU0MTgzMTUyNH0.C65pYkPKZisVqml3OxPs766x-n0Zf_58eQDQ_u3pxD4"
        );

        clientHeaders.putAll(provideHeaders());

        return new HttpClientArg(
            10,
            10,
            IApiCall.BASE_URL,
            clientHeaders);
    }

    public TApiCall getApiCall()
    {
        return apiCall;
    }

    public HttpClientArg getHttpClientArg()
    {
        return httpClientArg;
    }
}
