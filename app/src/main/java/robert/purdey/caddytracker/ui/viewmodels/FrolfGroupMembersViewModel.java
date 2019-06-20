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
import robert.purdey.caddytracker.ui.models.PlayerModel;

public class FrolfGroupMembersViewModel extends ViewModel
{
    private MutableLiveData<List<PlayerModel>> groupMembers;
    private IFrolfGroupController frolfGroupController;

    public FrolfGroupMembersViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        frolfGroupController = new FrolfGroupController(apiCallService);
    }

    public LiveData<List<PlayerModel>> getFrolfGroupMembers(UUID groupId)
    {
        groupMembers = new MutableLiveData<>();
        loadFrolfGroupMembers(groupId);

        return groupMembers;
    }

    private void loadFrolfGroupMembers(UUID groupId)
    {
        groupMembers = frolfGroupController.getGroupMembers(groupId);
    }
}
