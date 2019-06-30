package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.domain.holescores.HoleScoreFilterModel;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;


public interface IHoleScoreCall
{
    // todo: open up this endpoint
    //@GET("api/holescores/")
    //Call<List<HoleScoreModel>> getAll(
    //    @Header("Authorization") String auth);

    @POST("api/holescores/filter")
    Call<EncryptModel> getWithFilter(
        @Header("Authorization") String auth,
        @Body EncryptModel filter);
}
