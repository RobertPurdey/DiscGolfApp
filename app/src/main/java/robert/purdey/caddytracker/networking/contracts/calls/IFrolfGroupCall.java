package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public interface IFrolfGroupCall extends IApiCall
{
    @GET("api/frolfgroups/")
    Call<List<FrolfGroupModel>> getFrolfGroups();
}
