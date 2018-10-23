package robert.purdey.caddytracker.networking.services;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.networking.contracts.configs.IHttpClientConfig;
import robert.purdey.caddytracker.networking.contracts.configs.IRetrofitConfig;

public class ApiCallService implements IApiCallService
{
    private IRetrofitConfig retrofitConfig;
    IHttpClientConfig httpClientConfig;

    public ApiCallService(
        IRetrofitConfig retrofitConfig,
        IHttpClientConfig httpClientConfig)
    {
        this.retrofitConfig    = retrofitConfig;
        this.httpClientConfig  = httpClientConfig;
    }

    @Override
    public <T> T getApiCall(final HttpClientArg arg, final Class<T> apiCall)
    {
        OkHttpClient httpClient  = httpClientConfig.configure(arg);
        Retrofit retrofit        = retrofitConfig.configure(httpClient, arg.getBaseUrl());

        return retrofit.create(apiCall);
    }
}
