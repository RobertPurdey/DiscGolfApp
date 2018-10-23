package robert.purdey.caddytracker.networking.contracts.configs;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface IRetrofitConfig
{
    Retrofit configure(OkHttpClient client, String baseUrl);
}
