package robert.purdey.caddytracker.services;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import robert.purdey.caddytracker.ui.models.FriendModel;

public interface ApiCall
{
    //todo: use configuration file?
    String BASE_URL = "http://<myip>:53739/";

    @GET("api/appusers/friends")
    Call<List<FriendModel>> getFriends();
}
