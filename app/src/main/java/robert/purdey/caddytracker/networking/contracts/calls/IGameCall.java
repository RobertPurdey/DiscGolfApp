package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import robert.purdey.caddytracker.ui.models.GameModel;

public interface IGameCall
{
    @GET("api/games/")
    Call<List<GameModel>> getAll(
        @Header("Authorization") String auth);
}
