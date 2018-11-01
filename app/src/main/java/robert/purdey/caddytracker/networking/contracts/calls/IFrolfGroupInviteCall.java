package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public interface IFrolfGroupInviteCall
{
    //todo: use configuration file?
    String BASE_URL = "http://192.168.1.65:53739/";

    @GET("api/frolfgroupinvites/")
    Call<List<FrolfGroupInviteModel>> getFrolfGroupInvites();
}
