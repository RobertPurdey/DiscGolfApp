package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public interface IFrolfGroupCall
{
    //todo: use configuration file?
    String BASE_URL = "http://192.168.1.65:53739/";

    @GET("api/frolfgroups/")
    Call<List<FrolfGroupModel>> getFrolfGroups(
        @Header("Authorization") String auth);
}
