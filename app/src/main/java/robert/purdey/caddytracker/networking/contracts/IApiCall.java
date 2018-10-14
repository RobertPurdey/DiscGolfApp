package robert.purdey.caddytracker.networking.contracts;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import robert.purdey.caddytracker.ui.models.FriendModel;

public interface IApiCall
{
    //todo: use configuration file?
    String BASE_URL = "http://192.168.1.65:53739/";

    @GET("api/appusers/friends")
    Call<List<FriendModel>> getFriends();
}
