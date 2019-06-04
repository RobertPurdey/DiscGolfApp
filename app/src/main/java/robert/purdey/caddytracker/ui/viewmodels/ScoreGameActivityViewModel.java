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
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;
import robert.purdey.caddytracker.ui.models.GameResultModel;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;


public class ScoreGameActivityViewModel extends ViewModel
{
    public MutableLiveData<UUID> GameId;
    public MutableLiveData<Integer> CurrentHole;
    public MutableLiveData<Integer> MaxHole;
    public MutableLiveData<GameModel> Game;
    public MutableLiveData<String> CurrentHoleLbl;
    public MutableLiveData<String> ParLbl;

    private IGameController gameController;

    public ScoreGameActivityViewModel()
    {
        GameId          = new MutableLiveData<>();
        CurrentHole     = new MutableLiveData<>();
        MaxHole         = new MutableLiveData<>();
        Game            = new MutableLiveData<>();
        CurrentHoleLbl  = new MutableLiveData<>();
        ParLbl          = new MutableLiveData<>();

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

    public int getCurrentHole()
    {
        return CurrentHole.getValue();
    }

    public void setCurrentHole(int i)
    {
        int holePar = Game.getValue().getHolePars().get(i);

        CurrentHole.setValue(i);
        CurrentHoleLbl.setValue(Integer.toString(i));
        ParLbl.setValue(Integer.toString(holePar));
    }

    public LiveData<GameModel> getGame(UUID id)
    {
        Game = new MutableLiveData<>();
        loadGame(id);

        return Game;
    }

    public LiveData<GameResultModel> getGameResults(UUID id)
    {
        return gameController.getGameResults(id);
    }

    public int getNextHoleNumber()
    {
        return CurrentHole.getValue()+1;
    }

    public int getPrevHoleNumber()
    {
        return CurrentHole.getValue()-1;
    }

    public void SaveHoleScores(List<HoleScoreModel> holeScores, IApiResponseListener listener)
    {
        GameHoleUpdateModel updateModel = new GameHoleUpdateModel();

        updateModel.setGameId(Game.getValue().getIdKey());
        updateModel.setHoleScoreUpdates(holeScores);

        gameController.updateGameHoles(updateModel, listener);
    }

    public boolean CurrentUserIsCreator(UUID id)
    {
        GameModel model = Game.getValue();

        return model != null
            && model.getCreatorId().equals(id);
    }

    private void loadGame(UUID id)
    {
        Game = gameController.getGame(id);
    }
}
