package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
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
        HttpClientArg arg                      = getHttpClientArg();

        arg.setHeaders( new HashMap<>() );

        Call<TokenModel> tokenCall = GetCustomArgApiCall(arg).login(tokenFieldMap);

        tokenCall.enqueue(new Callback<TokenModel>() {
            @Override
            public void onResponse(Call<TokenModel> call, Response<TokenModel> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                    listener.onResponseSuccessful();

                    //todo: store login session
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
}
