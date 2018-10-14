package robert.purdey.caddytracker.networking;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robert.purdey.caddytracker.networking.contracts.IRetrofitBuilder;

public class RetrofitBuilder implements IRetrofitBuilder
{
    @Override
    public Retrofit build(OkHttpClient client, String baseUrl)
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
