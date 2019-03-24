package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;

public interface IGameCall
{
    @GET("api/games/")
    Call<List<GameModel>> getAll(
        @Header("Authorization") String auth);

    @POST("api/games/holeScores")
    Call<Void> updateGameHoles(
        @Header("Authorization") String auth,
        @Body GameHoleUpdateModel gameHoleUpdateModel);
}
