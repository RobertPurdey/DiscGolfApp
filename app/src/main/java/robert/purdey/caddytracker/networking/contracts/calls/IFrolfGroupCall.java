package robert.purdey.caddytracker.networking.contracts.calls;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;

public interface IFrolfGroupCall
{
    @GET("api/frolfgroups/")
    Call<EncryptModel> getFrolfGroups(
        @Header("Authorization") String auth);

    @POST("api/frolfgroups/getById")
    Call<EncryptModel> getById(
        @Header("Authorization") String auth,
        @Body EncryptModel id);

    @POST("api/frolfgroups/groupmembers/")
    Call<EncryptModel> getGroupMembers(
        @Header("Authorization") String auth,
        @Body EncryptModel id);

    @POST("api/frolfgroups/insert/")
    Call<EncryptModel> insert(
        @Header("Authorization") String auth,
        @Body EncryptModel groupModel);

    @POST("api/frolfgroups/update/")
    Call<Void> update(
        @Header("Authorization") String auth,
        @Body EncryptModel groupModel);

    @POST("api/frolfgroups/creategame/")
    Call<EncryptModel> createGame(
        @Header("Authorization") String auth,
        @Body EncryptModel creationModel);

    @POST("api/frolfgroups/leave/")
    Call<Void> leaveGroup(
        @Header("Authorization") String auth,
        @Body EncryptModel groupId);

    @POST("api/frolfgroups/removePlayer/")
    Call<Void> removePlayer(
        @Header("Authorization") String auth,
        @Body EncryptModel removePlayerModel);
}
