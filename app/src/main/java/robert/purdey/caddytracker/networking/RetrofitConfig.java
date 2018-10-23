package robert.purdey.caddytracker.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robert.purdey.caddytracker.networking.contracts.configs.IRetrofitConfig;

public class RetrofitConfig implements IRetrofitConfig
{
    @Override
    public Retrofit configure(OkHttpClient client, String baseUrl)
    {
        validateBaseUrl(baseUrl);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        return retrofit;
    }

    private void validateBaseUrl(String baseUrl)
    {

    }
}
