package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.Map;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import robert.purdey.caddytracker.ui.models.TokenModel;

public interface IAppUserCall
{
    //todo: use configuration file or centralize this somewhere else since api call interfaces cant extend?
    String BASE_URL = "http://192.168.1.65:53739/";

    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenModel> login(@FieldMap Map<String, String> loginAttempt);
}
