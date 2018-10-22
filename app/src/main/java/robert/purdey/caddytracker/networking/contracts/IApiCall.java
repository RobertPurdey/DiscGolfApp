package robert.purdey.caddytracker.networking.contracts;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import robert.purdey.caddytracker.ui.models.FriendModel;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.models.LoginModel;
import robert.purdey.caddytracker.ui.models.TokenModel;

public interface IApiCall
{
    //todo: use configuration file?
    String BASE_URL = "http://192.168.1.65:53739/";

    @FormUrlEncoded
    @POST("oauth2/token")
    Call<TokenModel> login(@FieldMap Map<String, String> loginAttempt);

    @GET("api/appusers/friends")
    Call<List<FriendModel>> getFriends();

    @GET("api/frolfgroups/")
    Call<List<FrolfGroupModel>> getFrolfGroups();
}
