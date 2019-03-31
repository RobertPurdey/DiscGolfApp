package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IGameController;
import robert.purdey.caddytracker.networking.controllers.GameController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.scorecard.PlayerGameResultModel;

public class ScoreCardViewModel extends ViewModel
{
    public MutableLiveData<UUID> GameId;
    public MutableLiveData<Integer> CurrentHole;
    public MutableLiveData<Integer> MaxHole;
    public MutableLiveData<List<PlayerGameResultModel>> PlayerResults;

    private IGameController gameController;

    public ScoreCardViewModel()
    {
        GameId          = new MutableLiveData<>();
        CurrentHole     = new MutableLiveData<>();
        MaxHole         = new MutableLiveData<>();
        PlayerResults   = new MutableLiveData<>();

        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        gameController = new GameController(apiCallService);
    }

    public UUID getGameId()
    {
        return GameId.getValue();
    }

    public void setGameId(UUID id)
    {
        GameId.setValue(id);
    }

    public LiveData<List<PlayerGameResultModel>> getPlayerResults(UUID gameId)
    {
        PlayerResults = new MutableLiveData<>();
        loadPlayerResults(gameId);

        return PlayerResults;
    }

    private void loadPlayerResults(UUID id)
    {
        PlayerResults = gameController.getPlayerResults(id);
    }
}
