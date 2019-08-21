package robert.purdey.caddytracker.networking.contracts.controllers;

import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.models.PlayerModel;

public interface IFrolfGroupController
{
    MutableLiveData<List<FrolfGroupModel>> getFrolfGroups();
    MutableLiveData<FrolfGroupModel> getFrolfGroup(UUID id);
    MutableLiveData<List<PlayerModel>> getGroupMembers(UUID groupId);
    MutableLiveData<FrolfGroupModel> insert(FrolfGroupModel model);
    void update(FrolfGroupModel model);
    void leaveGroup(UUID groupId, IApiResponseListener listener);
    void removePlayer(UUID groupId, UUID playerId, IApiResponseListener listener);
}
