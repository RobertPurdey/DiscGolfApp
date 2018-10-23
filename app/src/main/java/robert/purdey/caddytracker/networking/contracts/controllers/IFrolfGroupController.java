package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public interface IFrolfGroupController
{
    MutableLiveData<List<FrolfGroupModel>> getFrolfGroups();
}
