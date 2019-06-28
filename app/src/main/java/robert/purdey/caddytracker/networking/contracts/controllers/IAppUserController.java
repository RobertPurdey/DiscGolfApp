package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.security.interfaces.RSAPublicKey;
import java.util.Map;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.AppUserCreationModel;
import robert.purdey.caddytracker.ui.models.AppUserModel;
import robert.purdey.caddytracker.ui.models.AppUserUpdateModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public interface IAppUserController
{
    MutableLiveData<TokenModel> login(
        IApiResponseListener listener,
        Map<String, String> tokenFieldMap);

    MutableLiveData<AppUserModel> getCurrentUserInfo(
        IApiResponseListener listener);

    void createAccount(
        AppUserCreationModel userCreateRequest,
        IApiResponseListener listener);

    void updateAccount(
        AppUserUpdateModel updateAccountModel,
        IApiResponseListener listener);

    void setNewPublicKey(
        RSAPublicKey rsaPublicKey,
        IApiResponseListener listener);
}
