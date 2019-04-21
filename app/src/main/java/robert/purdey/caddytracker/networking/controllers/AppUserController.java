package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.storage.UserSessionManager;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.AppUserCreationModel;
import robert.purdey.caddytracker.ui.models.AppUserModel;
import robert.purdey.caddytracker.ui.models.TokenModel;


public class AppUserController
    extends ApiController<IAppUserCall>
    implements IAppUserController
{
    public AppUserController(
        IApiCallService apiCallService)
    {
        super(apiCallService, IAppUserCall.class);
    }

    // todo: return proper token
    public MutableLiveData<TokenModel> login(
        IApiResponseListener listener,
        Map<String, String> tokenFieldMap)
    {
        final MutableLiveData<TokenModel> data = new MutableLiveData<>();
        Call<TokenModel> tokenCall = getApiCall().login(tokenFieldMap);

        tokenCall.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response)
            {
                if ( response.isSuccessful() )
                {
                    // todo: should token be stored a different way?
                    UserSessionManager userSession = FrolfApp.getUserSession();

                    userSession.storeToken(response.body().accessToken);
                    userSession.storeRefreshToken(response.body().refreshToken);

                    data.setValue(response.body());
                    listener.onResponseSuccessful();
                }
                else
                {
                    listener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<TokenModel> call, Throwable t)
            {
                listener.onCallFailure();
            }
        });

        return data;
    }

    // todo: return proper token
    public MutableLiveData<AppUserModel> getCurrentUserInfo(
        IApiResponseListener listener)
    {
        final MutableLiveData<AppUserModel> data = new MutableLiveData<>();
        Call<AppUserModel> userCall = getApiCall().getCurrentUserInfo(getAuthorizationHeader());

        userCall.enqueue(new Callback<AppUserModel>() {
            @Override
            public void onResponse(Call<AppUserModel> call, Response<AppUserModel> response)
            {
                if ( response.isSuccessful() )
                {
                    // todo: should token be stored a different way?
                    UserSessionManager userSession = FrolfApp.getUserSession();

                    userSession.storeCurrentUserId(response.body().getIdKey());

                    data.setValue(response.body());
                    listener.onResponseSuccessful();
                }
                else
                {
                    listener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<AppUserModel> call, Throwable t)
            {
                listener.onCallFailure();
            }
        });

        return data;
    }

    public void createAccount(
        AppUserCreationModel userCreateRequest,
        IApiResponseListener listener)
    {
        Call<Void> createAccountCall = getApiCall().createAccount(userCreateRequest);

        createAccountCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    listener.onResponseSuccessful();
                }
                else
                {
                    listener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                listener.onCallFailure();
            }
        });
    }
}
