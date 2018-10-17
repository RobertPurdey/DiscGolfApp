package robert.purdey.caddytracker.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IApiCallConfig;
import robert.purdey.caddytracker.networking.contracts.IRetrofitBuilder;
import robert.purdey.caddytracker.networking.contracts.IHttpClientConfig;
import robert.purdey.caddytracker.networking.contracts.IRetrofitConfig;

public class RetrofitBuilder implements IRetrofitBuilder
{
    private IRetrofitConfig retrofitBuilder;
    private IHttpClientConfig httpClientBuilder;

    public RetrofitBuilder(
        IRetrofitConfig retroFitBuilder,
        IHttpClientConfig httpClientBuilder)
    {
        this.retrofitBuilder    = retroFitBuilder;
        this.httpClientBuilder  = httpClientBuilder;
    }

    @Override
    public Retrofit build(HttpClientArg arg)
    {
        OkHttpClient httpClient = httpClientBuilder.configure(arg);

        return retrofitBuilder.configure(httpClient, arg.getBaseUrl());
    }
}
