package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.models.PlayerModel;

public interface IFrolfGroupCall
{
    @GET("api/frolfgroups/")
    Call<List<FrolfGroupModel>> getFrolfGroups(
        @Header("Authorization") String auth);

    @GET("api/frolfgroups/{id}/")
    Call<FrolfGroupModel> getById(
        @Header("Authorization") String auth,
        @Path("id") UUID id);

    @GET("api/frolfgroups/groupmembers/{id}/")
    Call<List<PlayerModel>> getGroupMembers(
        @Header("Authorization") String auth,
        @Path("id") UUID id);

    @POST("api/frolfgroups/insert/")
    Call<FrolfGroupModel> insert(
        @Header("Authorization") String auth,
        @Body FrolfGroupModel groupModel);

    @POST("api/frolfgroups/update/")
    Call<FrolfGroupModel> update(
        @Header("Authorization") String auth,
        @Body FrolfGroupModel groupModel);
}
