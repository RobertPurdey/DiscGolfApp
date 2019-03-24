package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;

import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;

public interface IGameController
{
    MutableLiveData<List<GameModel>> getAll();
    void updateGameHoles(GameHoleUpdateModel model);
}
