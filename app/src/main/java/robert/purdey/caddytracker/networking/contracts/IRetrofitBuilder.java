package robert.purdey.caddytracker.networking.contracts;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public interface IRetrofitBuilder
{
    Retrofit build(OkHttpClient client, String baseUrl);
}
