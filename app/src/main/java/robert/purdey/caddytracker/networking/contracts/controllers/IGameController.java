package robert.purdey.caddytracker.networking.contracts.controllers;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.domain.games.GameFilter;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameCreationModel;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;
import robert.purdey.caddytracker.ui.models.GameResultModel;

public interface IGameController
{
    MutableLiveData<List<GameModel>> getAll();
    MutableLiveData<List<GameModel>> getWithFilter(GameFilter filter);
    MutableLiveData<GameResultModel> getGameResults(UUID id);
    MutableLiveData<GameModel> getGame(UUID id);
    void updateGameHoles(GameHoleUpdateModel model, IApiResponseListener listener);
    void completeGame(UUID id, IApiResponseListener listener);
    MutableLiveData<GameModel> createGame(GameCreationModel model);
}
