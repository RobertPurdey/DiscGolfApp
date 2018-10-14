package robert.purdey.caddytracker.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IApiCallBuilder;
import robert.purdey.caddytracker.networking.contracts.IApiCallManager;
import robert.purdey.caddytracker.networking.contracts.IHttpClientBuilder;
import robert.purdey.caddytracker.networking.contracts.IRetrofitBuilder;

public class ApiCallManager implements IApiCallManager
{
    private IApiCallBuilder apiCallBuilder;
    private IRetrofitBuilder retrofitBuilder;
    private IHttpClientBuilder httpClientBuilder;

    public ApiCallManager(
        IApiCallBuilder apiCallBuilder,
        IRetrofitBuilder retroFitBuilder,
        IHttpClientBuilder httpClientBuilder)
    {
        this.apiCallBuilder     = apiCallBuilder;
        this.retrofitBuilder    = retroFitBuilder;
        this.httpClientBuilder  = httpClientBuilder;
    }

    @Override
    public IApiCall build(HttpClientArg arg)
    {
        OkHttpClient httpClient = httpClientBuilder.build(arg);
        // todo: better handling of providing base url?
        Retrofit retrofit       = retrofitBuilder.build(httpClient, IApiCall.BASE_URL);

        return apiCallBuilder.build(retrofit);
    }
}
