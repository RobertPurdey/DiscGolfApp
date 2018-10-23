package robert.purdey.caddytracker.networking;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.configs.IHttpClientConfig;

/**
 * Builds an OkHttpClient capable of being consumed by a Retrofit call.
 */
public class HttpClientConfig implements IHttpClientConfig
{
    @Override
    public OkHttpClient configure(HttpClientArg arg)
    {
        validateArgument(arg);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder
            .readTimeout(arg.getReadTimeout(), TimeUnit.SECONDS)
            .connectTimeout(arg.getConnectionTimeout(), TimeUnit.SECONDS)
            .addInterceptor(chain ->
            {
                Request request = buildRequest(chain.request(), arg);
                return chain.proceed(request);
            });

        return okHttpClientBuilder.build();
    }

    private Request buildRequest(Request request, HttpClientArg arg)
    {
        final Request.Builder newRequest = request.newBuilder();

        arg.getHeaders().forEach(newRequest::header);

        return newRequest.build();
    }

    // todo: should use a validator?? when  it gets more complex
    // throw exceptions as needed
    private void validateArgument(HttpClientArg arg)
    {
        boolean isValid = false;
    }
}
