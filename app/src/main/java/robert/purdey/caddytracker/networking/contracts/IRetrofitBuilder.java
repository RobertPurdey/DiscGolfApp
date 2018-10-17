package robert.purdey.caddytracker.networking.contracts;

import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;

public interface IRetrofitBuilder
{
    Retrofit build(HttpClientArg arg);
}
