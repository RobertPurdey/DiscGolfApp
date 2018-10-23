package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.calls.IApiCall;
import robert.purdey.caddytracker.networking.contracts.calls.IAppUserCall;
import robert.purdey.caddytracker.networking.contracts.calls.IFrolfGroupCall;
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
