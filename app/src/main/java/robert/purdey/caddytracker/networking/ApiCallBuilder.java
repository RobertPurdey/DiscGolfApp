package robert.purdey.caddytracker.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IApiCallConfig;
import robert.purdey.caddytracker.networking.contracts.IApiCallBuilder;
import robert.purdey.caddytracker.networking.contracts.IHttpClientConfig;
import robert.purdey.caddytracker.networking.contracts.IRetrofitConfig;

public class ApiCallBuilder implements IApiCallBuilder
{
    private IApiCallConfig apiCallBuilder;
    private IRetrofitConfig retrofitBuilder;
    private IHttpClientConfig httpClientBuilder;

    public ApiCallBuilder(
        IApiCallConfig apiCallBuilder,
        IRetrofitConfig retroFitBuilder,
        IHttpClientConfig httpClientBuilder)
    {
        this.apiCallBuilder     = apiCallBuilder;
        this.retrofitBuilder    = retroFitBuilder;
        this.httpClientBuilder  = httpClientBuilder;
    }

    @Override
    public IApiCall build(HttpClientArg arg)
    {
        OkHttpClient httpClient = httpClientBuilder.configure(arg);
        // todo: better handling of providing base url?
        Retrofit retrofit       = retrofitBuilder.configure(httpClient, IApiCall.BASE_URL);

        return apiCallBuilder.configure(retrofit);
    }
}
