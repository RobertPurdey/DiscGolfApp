package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameCreationModel;
import robert.purdey.caddytracker.ui.models.GameModel;

public class NewGameViewModel extends ViewModel
{
    private MutableLiveData<GameCreationModel> creationModel;
    private IFrolfGroupController frolfGroupController;
    public MutableLiveData<String> gameName;

    public NewGameViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        frolfGroupController = new FrolfGroupController(apiCallService);
        creationModel        = new MutableLiveData<>();
        gameName             = new MutableLiveData<>();

        creationModel.setValue( new GameCreationModel() );
    }

    public LiveData<GameModel> CreateGame()
    {
        creationModel.getValue().setName(gameName.getValue());

        return frolfGroupController.createGame( creationModel.getValue());
    }

    public void setCourse(UUID id)
    {
        creationModel.getValue().setCourseId(id);
    }

    public void setGroup(UUID id)
    {
        creationModel.getValue().setGroupId(id);
    }

    public UUID getGroupId()
    {
        return creationModel.getValue().getGroupId();
    }

    public void clearPlayers()
    {
        creationModel.getValue().getPlayerIds().clear();
    }

    public void managePlayer(UUID id)
    {
        List<UUID> playerIds = creationModel.getValue().PlayerIds;

        if ( playerIds.contains(id) )
        {
            removePlayer(id);
        }
        else
        {
            addPlayer(id);
        }
    }

    private void addPlayer(UUID id)
    {
        creationModel.getValue().getPlayerIds().add(id);
    }

    private void removePlayer(UUID id)
    {
        creationModel.getValue().getPlayerIds().remove(id);
    }
}
