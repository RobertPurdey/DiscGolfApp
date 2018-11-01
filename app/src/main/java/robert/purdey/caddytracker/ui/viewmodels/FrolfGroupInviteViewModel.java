package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupInviteController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupInviteController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class FrolfGroupInviteViewModel extends ViewModel
{
    private MutableLiveData<List<FrolfGroupInviteModel>> frolfGroupInvites;
    private IFrolfGroupInviteController frolfGroupInviteController;

    public FrolfGroupInviteViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        frolfGroupInviteController = new FrolfGroupInviteController(apiCallService);
    }

    public LiveData<List<FrolfGroupInviteModel>> getFrolfGroupInvites()
    {
        // todo: maybe this check requires isDirty??
        if (frolfGroupInvites == null)
        {
            frolfGroupInvites = new MutableLiveData<>();
            loadFrolfGroups();
        }

        return frolfGroupInvites;
    }

    private void loadFrolfGroups()
    {
        frolfGroupInvites = frolfGroupInviteController.getFrolfGroupInvites();
    }
}
