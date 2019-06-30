package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.domain.storage.UserSessionManager;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IAppUserController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.security.encryption.AesManager;
import robert.purdey.caddytracker.security.encryption.RsaManager;
import robert.purdey.caddytracker.ui.FrolfApp;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.AppUserCreationModel;
import robert.purdey.caddytracker.ui.models.AppUserModel;
import robert.purdey.caddytracker.ui.models.AppUserUpdateModel;
import robert.purdey.caddytracker.ui.models.Keys.PublicKeyModel;
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

    public MutableLiveData<AppUserModel> getCurrentUserInfo(
        IApiResponseListener listener)
    {
        final MutableLiveData<AppUserModel> data = new MutableLiveData<>();
        Call<EncryptModel> userCall = getApiCall().getCurrentUserInfo(getAuthorizationHeader());

        userCall.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    AppUserModel model = decryptModel(response.body(), AppUserModel.class);
                    data.setValue(model);

                    listener.onResponseSuccessful();
                }
                else
                {
                    listener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
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
        EncryptModel encryptModel     = encryptModel(userCreateRequest);
        Call<Void> createAccountCall  = getApiCall().createAccount(encryptModel);

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

    public void updateAccount(
        AppUserUpdateModel updateModel,
        IApiResponseListener listener)
    {
        EncryptModel encryptModel     = encryptModel(updateModel);
        Call<Void> updateAccountCall  = getApiCall().updateAccount(encryptModel);

        updateAccountCall.enqueue(new Callback<Void>() {
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

    public void setNewPublicKey(
        RSAPublicKey rsaPublicKey,
        IApiResponseListener listener)
    {
        PublicKeyModel keyModel = new PublicKeyModel();
        keyModel.setXmlRsaPublicKey( ConvertRsaPublicKeyToXml(rsaPublicKey) );

        EncryptModel encryptModel     = encryptModel(keyModel);
        Call<Void> updateAccountCall  = getApiCall().setNewPublicKey(encryptModel);

        updateAccountCall.enqueue(new Callback<Void>() {
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

    private String ConvertRsaPublicKeyToXml(RSAPublicKey key)
    {
        byte[] modBytes  = key.getModulus().toByteArray();
        byte[] stripSign = new byte[modBytes.length - 1];

        System.arraycopy(modBytes, 1, stripSign, 0, modBytes.length - 1);
        String modBase64 = Base64.getEncoder().encodeToString(stripSign);

        byte[] pubBytes  = key.getPublicExponent().toByteArray();
        String pubBase64 = Base64.getEncoder().encodeToString(pubBytes);

        return "<RSAKeyValue>"
             +     "<Modulus>"  + modBase64 + "</Modulus>"
             +     "<Exponent>" + pubBase64 + "</Exponent>"
             + "</RSAKeyValue>";
    }
}
