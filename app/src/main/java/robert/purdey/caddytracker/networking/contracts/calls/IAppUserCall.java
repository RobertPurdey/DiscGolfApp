package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.ui.activities.CreateAccountActivity;
import robert.purdey.caddytracker.ui.models.AppUserCreationModel;
import robert.purdey.caddytracker.ui.models.AppUserModel;
import robert.purdey.caddytracker.ui.models.AppUserUpdateModel;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.models.Keys.PublicKeyModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public interface IAppUserCall
{
    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenModel> login(
        @FieldMap Map<String, String> loginAttempt);

    @GET("api/appusers/info")
    Call<EncryptModel> getCurrentUserInfo(
        @Header("Authorization") String auth);

    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenModel> refreshToken(
        @FieldMap Map<String, String> refreshTokenAttempt);

    @POST("api/appusers/create/account")
    Call<Void> createAccount(
        @Body EncryptModel userCreateRequest);

    @POST("api/appusers/update/account")
    Call<Void> updateAccount(
        @Body AppUserUpdateModel userUpdateRequest);

    @POST("api/appusers/setPublicKey")
    Call<Void> setNewPublicKey(
        @Body PublicKeyModel publicKeyModel);
}
