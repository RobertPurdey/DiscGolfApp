package robert.purdey.caddytracker.networking;

import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IApiCallConfig;

public class ApiCallConfig implements IApiCallConfig
{
    @Override
    public IApiCall configure(Retrofit retrofit)
    {
        return retrofit.create(IApiCall.class);
    }
}
