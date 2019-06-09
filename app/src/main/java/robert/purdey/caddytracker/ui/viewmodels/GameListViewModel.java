package robert.purdey.caddytracker.ui.viewmodels;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import robert.purdey.caddytracker.domain.games.GameFilter;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IGameController;
import robert.purdey.caddytracker.networking.controllers.GameController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.GameModel;

public class GameListViewModel extends ViewModel
{
    private MutableLiveData<List<GameModel>> games;
    private IGameController gameGroupController;

    public GameListViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        gameGroupController = new GameController(apiCallService);
    }

    public LiveData<List<GameModel>> getGames(GameFilter filter)
    {
        // todo: maybe this check requires isDirty??
        if (games == null)
        {
            games = new MutableLiveData<>();
            loadGames(filter);
        }

        return games;
    }

    private void loadGames(GameFilter filter)
    {
        games = gameGroupController.getWithFilter(filter);
    }
}
