package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public interface IFrolfGroupController
{
    MutableLiveData<List<FrolfGroupModel>> getFrolfGroups();
    MutableLiveData<FrolfGroupModel> getFrolfGroup(UUID id);
    MutableLiveData<FrolfGroupModel> insert(FrolfGroupModel model);
    MutableLiveData<FrolfGroupModel> update(FrolfGroupModel model);
}
