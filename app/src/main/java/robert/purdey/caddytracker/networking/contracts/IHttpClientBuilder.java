package robert.purdey.caddytracker.networking.contracts;


import okhttp3.OkHttpClient;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;

public interface IHttpClientBuilder
{
    OkHttpClient build(HttpClientArg arg);
}
