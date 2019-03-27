package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;

public interface IGameController
{
    MutableLiveData<List<GameModel>> getAll();
    MutableLiveData<GameModel> getGame(UUID id);
    void updateGameHoles(GameHoleUpdateModel model, IApiResponseListener listener);
}
