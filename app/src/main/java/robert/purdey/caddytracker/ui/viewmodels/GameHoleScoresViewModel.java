package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.domain.holescores.HoleScoreFilterModel;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IGameController;
import robert.purdey.caddytracker.networking.contracts.controllers.IHoleScoreController;
import robert.purdey.caddytracker.networking.controllers.GameController;
import robert.purdey.caddytracker.networking.controllers.HoleScoreController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;

public class GameHoleScoresViewModel extends ViewModel
{
    public MutableLiveData<UUID> GameId;
    private MutableLiveData<List<HoleScoreModel>> HoleScores;
    private IHoleScoreController HoleScoreController;

    public GameHoleScoresViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        GameId              = new MutableLiveData<>();
        HoleScoreController = new HoleScoreController(apiCallService);
    }

    public LiveData<List<HoleScoreModel>> getHoleScores(UUID gameId, int holeNumber, boolean isReset)
    {
        if (HoleScores == null || isReset)
        {
            HoleScores = new MutableLiveData<>();
            loadHoleScores(gameId, holeNumber);
        }

        return HoleScores;
    }

    private void loadHoleScores(UUID gameId, int holeNumber)
    {
        HoleScoreFilterModel filter = new HoleScoreFilterModel();

        filter.setGameID(gameId);
        filter.setHoleNumber(holeNumber);

        HoleScores = HoleScoreController.getWithFilter(filter);
    }
}
