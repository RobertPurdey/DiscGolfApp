package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;
import robert.purdey.caddytracker.ui.models.scorecard.PlayerGameResultModel;

public interface IGameCall
{
    @GET("api/games/")
    Call<List<GameModel>> getAll(
        @Header("Authorization") String auth);

    @GET("api/games/{id}/")
    Call<GameModel> getById(
        @Header("Authorization") String auth,
        @Path("id") UUID id);

    @POST("api/games/holeScores")
    Call<Void> updateGameHoles(
        @Header("Authorization") String auth,
        @Body GameHoleUpdateModel gameHoleUpdateModel);

    @GET("api/games/{id}/playerresults/")
    Call<List<PlayerGameResultModel>> getPlayerResults(
        @Header("Authorization") String auth,
        @Path("id") UUID id);
}
