package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.domain.frolfgroups.FrolfGroupInviteFilterModel;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;
import robert.purdey.caddytracker.ui.models.InviteCreationModel;

public interface IFrolfGroupInviteCall
{
    @GET("api/frolfgroupinvites/")
    Call<EncryptModel> getAll(
        @Header("Authorization") String auth);

    @POST("api/frolfgroupinvites/filter")
    Call<EncryptModel> getWithFilter(
        @Header("Authorization") String auth,
        @Body EncryptModel filter);

    @POST("api/frolfgroupinvites/accept")
    Call<Void> accept(
        @Header("Authorization") String auth,
        @Body EncryptModel inviteId);

    @DELETE("api/frolfgroupinvites/delete")
    Call<Void> remove(
        @Header("Authorization") String auth,
        @Body EncryptModel inviteId);

    @POST("api/frolfgroupinvites/send")
    Call<Void> send(
        @Header("Authorization") String auth,
        @Body EncryptModel creationModel);
}
