package robert.purdey.caddytracker.networking.authentication;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.models.RefreshTokenModel;
import robert.purdey.caddytracker.ui.models.TokenModel;
import robert.purdey.caddytracker.utilities.Strings;

public class TokenRefresher implements Authenticator
{
    private IApiCallService apiCallService;

    public TokenRefresher(IApiCallService apiCallService)
    {
        this.apiCallService = apiCallService;
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException
    {
        // Only attempt to refresh the token 3 times
        if ( responseCount(response) >= 3 ) return null;

        // todo: check if the token has been refreshed to stop multiple refreshes
        synchronized ( this )
        {
            // Make it as retrofit synchronous call as we need to wait for the new token to proceed
            retrofit2.Response<TokenModel> refreshResponse = buildRefreshCall().execute();

            if (refreshResponse != null && refreshResponse.code() == 200)
            {
                // todo: store new token in share pref
                TokenModel newToken = refreshResponse.body();
            }
        }

        // Update last request with the new authorization token
        String newAccessToken = getAccessToken();
        Request newRequest    = null;

        if ( !Strings.isNullOrEmpty(newAccessToken) )
        {
            newRequest = response.request().newBuilder()
                .header("authorization", "Bearer " + newAccessToken)
                .build();
        }

        return newRequest;
    }

    private int responseCount(Response response) {
        int result = 1;

        while ( (response = response.priorResponse()) != null )
        {
            result++;
        }

        return result;
    }

    private Call<TokenModel> buildRefreshCall()
    {
        HttpClientArg arg = new HttpClientArg(
            10,
            10,
            IApiCall.BASE_URL,
            null );

        // todo: get refresh token from shared preferences here
        RefreshTokenModel refreshToken = new RefreshTokenModel("842dab33ec224cfdb0b9ff9933e30d8e");

        return apiCallService
            .getApiCall(arg, IAppUserCall.class)
            .refreshToken(refreshToken.getRequestFields());
    }

    private String getAccessToken()
    {
        return null;
    }
}
