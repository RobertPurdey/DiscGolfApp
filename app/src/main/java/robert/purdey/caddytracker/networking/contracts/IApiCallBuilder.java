package robert.purdey.caddytracker.networking.contracts;

import retrofit2.Retrofit;

public interface IApiCallBuilder
{
    IApiCall build(Retrofit retrofit);
}

