package robert.purdey.caddytracker.networking;

import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IApiCallBuilder;

public class ApiCallBuilder implements IApiCallBuilder
{
    @Override
    public IApiCall build(Retrofit retrofit)
    {
        return retrofit.create(IApiCall.class);
    }
}
