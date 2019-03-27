package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.Map;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.AppUserModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public interface IAppUserController
{
    // todo: better way to provide listener to an api call (one that doesnt couple viewmodel with api call (daz bad!).
    MutableLiveData<TokenModel> login(
        IApiResponseListener listener,
        Map<String, String> tokenFieldMap);

    MutableLiveData<AppUserModel> getCurrentUserInfo(
        IApiResponseListener listener);
}
