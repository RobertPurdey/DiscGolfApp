package robert.purdey.caddytracker.networking.contracts.configs;


import okhttp3.OkHttpClient;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;

public interface IHttpClientConfig
{
    OkHttpClient configure(HttpClientArg arg);
}
