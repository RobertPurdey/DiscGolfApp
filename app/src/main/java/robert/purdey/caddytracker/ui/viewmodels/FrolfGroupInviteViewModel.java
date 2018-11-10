package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.List;
import java.util.UUID;
import robert.purdey.caddytracker.domain.frolfgroups.FrolfGroupInviteFilterModel;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupInviteController;
import robert.purdey.caddytracker.networking.controllers.FrolfGroupInviteController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.listeners.IRefreshListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class FrolfGroupInviteViewModel extends ViewModel
{
    private MutableLiveData<List<FrolfGroupInviteModel>> frolfGroupInvites;
    private IFrolfGroupInviteController frolfGroupInviteController;
    private FrolfGroupInviteFilterModel filterModel;

    public FrolfGroupInviteViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        frolfGroupInviteController = new FrolfGroupInviteController(apiCallService);
        filterModel                = new FrolfGroupInviteFilterModel();
    }

    public LiveData<List<FrolfGroupInviteModel>> getFrolfGroupInvites()
    {
        if (frolfGroupInvites == null)
        {
            frolfGroupInvites = new MutableLiveData<>();
            loadFrolfGroupInvites();
        }

        return frolfGroupInvites;
    }

    public void acceptGroupInvite(UUID inviteId, IRefreshListener listener)
    {
        frolfGroupInviteController.accept(inviteId, new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                resetInviteData();
                listener.onRefresh();
            }

            @Override
            public void onResponseFailed()
            {
                // todo: implement this?
                // perhaps a toast message
            }

            @Override
            public void onCallFailure()
            {
                // todo: implement this?
                // perhaps a toast message
            }
        });
    }

    private void loadFrolfGroupInvites()
    {
        frolfGroupInvites = frolfGroupInviteController.getWithFilter(filterModel);
    }

    private void resetInviteData()
    {
        frolfGroupInvites = null;
        getFrolfGroupInvites();
    }


    private void declineGroupInvite()
    {

    }
}
