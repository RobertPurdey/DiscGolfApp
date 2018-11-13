package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.contracts.calls.IFrolfGroupCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public class FrolfGroupController
    extends ApiController<IFrolfGroupCall>
    implements IFrolfGroupController
{
    public FrolfGroupController(
        IApiCallService apiCallService)
    {
        super(apiCallService, IFrolfGroupCall.class);
    }

    public MutableLiveData<List<FrolfGroupModel>> getFrolfGroups()
    {
        final MutableLiveData<List<FrolfGroupModel>> data  = new MutableLiveData<>();
        Call<List<FrolfGroupModel>> caller = getApiCall().getFrolfGroups(getAuthorizationHeader());

        caller.enqueue(new Callback<List<FrolfGroupModel>>() {
            @Override
            public void onResponse(
                Call<List<FrolfGroupModel>> call,
                Response<List<FrolfGroupModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<List<FrolfGroupModel>> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }
}
