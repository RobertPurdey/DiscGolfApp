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
import robert.purdey.caddytracker.ui.models.GameResultModel;
import robert.purdey.caddytracker.ui.models.scorecard.PlayerGameResultModel;

public class ScoreCardViewModel extends ViewModel
{
    public MutableLiveData<UUID> GameId;
    public MutableLiveData<Integer> CurrentHole;
    public MutableLiveData<Integer> MaxHole;
    public MutableLiveData<GameResultModel> GameResults;

    private IGameController gameController;

    public ScoreCardViewModel()
    {
        GameId          = new MutableLiveData<>();
        CurrentHole     = new MutableLiveData<>();
        MaxHole         = new MutableLiveData<>();
        GameResults     = new MutableLiveData<>();

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

    public LiveData<GameResultModel> getGameResults(UUID gameId)
    {
        GameResults = new MutableLiveData<>();
        loadGameResults(gameId);

        return GameResults;
    }

    private void loadGameResults(UUID id)
    {
        GameResults = gameController.getGameResults(id);
    }
}
