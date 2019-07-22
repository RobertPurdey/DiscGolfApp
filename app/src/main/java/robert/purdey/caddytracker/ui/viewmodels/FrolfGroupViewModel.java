package robert.purdey.caddytracker.ui.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public class FrolfGroupViewModel extends ViewModel
{
    private MutableLiveData<List<FrolfGroupModel>> frolfGroups;
    private IFrolfGroupController frolfGroupController;

    public FrolfGroupViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        frolfGroupController = new FrolfGroupController(apiCallService);
    }

    public LiveData<List<FrolfGroupModel>> getFrolfGroups()
    {
        // todo: maybe this check requires isDirty??
        if (frolfGroups == null)
        {
            frolfGroups = new MutableLiveData<>();
            loadFrolfGroups();
        }

        return frolfGroups;
    }

    private void loadFrolfGroups()
    {
        frolfGroups = frolfGroupController.getFrolfGroups();
    }
}
