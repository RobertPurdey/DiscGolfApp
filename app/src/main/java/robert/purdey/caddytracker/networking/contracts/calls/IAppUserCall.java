package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import robert.purdey.caddytracker.ui.models.TokenModel;

public interface IAppUserCall extends IApiCall
{
    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenModel> login(@FieldMap Map<String, String> loginAttempt);
}
