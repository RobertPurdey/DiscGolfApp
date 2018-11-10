package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.domain.frolfgroups.FrolfGroupInviteFilterModel;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public interface IFrolfGroupInviteController
{
    MutableLiveData<List<FrolfGroupInviteModel>> getAll();
    MutableLiveData<List<FrolfGroupInviteModel>> getWithFilter(FrolfGroupInviteFilterModel filter);
    void accept(UUID inviteId, IApiResponseListener responseListener);
    void remove(UUID inviteId, IApiResponseListener responseListener);
}
