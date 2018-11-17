package robert.purdey.caddytracker.networking.authentication;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
import retrofit2.Call;
import robert.purdey.caddytracker.domain.storage.UserSessionManager;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.models.RefreshTokenModel;
import robert.purdey.caddytracker.ui.models.TokenModel;
import robert.purdey.caddytracker.utilities.Strings;

public class TokenRefresher implements Authenticator
{
    private IApiCallService apiCallService;
    private UserSessionManager userSession;

    public TokenRefresher(IApiCallService apiCallService)
    {
        this.apiCallService = apiCallService;
        userSession = FrolfApp.getUserSession();
    }

    @Override
    public Request authenticate(Route route, Response response) throws IOException
    {
        if ( responseCount(response) >= 3 )
        {
            return null;
        }

        Call<TokenModel> refreshCall = buildRefreshCall();

        // todo: check if the token has been refreshed to stop multiple refreshes
        synchronized ( this )
        {
            if ( !userSession.isValidUserSession() )
            {
                retrofit2.Response<TokenModel> refreshResponse = refreshCall.execute();

                if (refreshResponse != null && refreshResponse.code() == 200)
                {
                    storeSession(refreshResponse.body());
                }
            }
        }

        // Update last request with the new authorization token
        String newAccessToken = userSession.getToken();
        Request newRequest    = null;

        if ( !Strings.isNullOrEmpty(newAccessToken) )
        {
            newRequest = response.request().newBuilder()
                .header("authorization", "Bearer " + FrolfApp.getUserSession().getToken())
                .build();
        }

        return newRequest;
    }

    private void storeSession(TokenModel model)
    {
        userSession.storeToken(model.accessToken);
        userSession.storeRefreshToken(model.refreshToken);
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

        RefreshTokenModel refreshToken = new RefreshTokenModel(userSession.getRefreshToken());

        // clear session so future builds do not reuse the refresh token
        userSession.clearUserSession();

        return apiCallService
            .getApiCall(arg, IAppUserCall.class)
            .refreshToken(refreshToken.getRequestFields());
    }
}
