package robert.purdey.caddytracker.networking.contracts;

import retrofit2.Retrofit;

public interface IApiCallConfig
{
    IApiCall configure(Retrofit retrofit);
}

