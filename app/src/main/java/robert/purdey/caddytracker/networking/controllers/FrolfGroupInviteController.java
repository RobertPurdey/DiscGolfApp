package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.contracts.calls.IFrolfGroupInviteCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupInviteController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class FrolfGroupInviteController
    extends ApiController<IFrolfGroupInviteCall>
    implements IFrolfGroupInviteController
{
    public FrolfGroupInviteController(
        IApiCallService apiCallService)
    {
        super(apiCallService, IFrolfGroupInviteCall.class);
    }

    @Override
    public MutableLiveData<List<FrolfGroupInviteModel>> getFrolfGroupInvites()
    {
        final MutableLiveData<List<FrolfGroupInviteModel>> data  = new MutableLiveData<>();
        Call<List<FrolfGroupInviteModel>> caller                 = getApiCall().getFrolfGroupInvites();

        caller.enqueue(new Callback<List<FrolfGroupInviteModel>>() {
            @Override
            public void onResponse(
                Call<List<FrolfGroupInviteModel>> call,
                Response<List<FrolfGroupInviteModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<List<FrolfGroupInviteModel>> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve group invites because you are a loser and have none!");
            }
        });

        return data;
    }
}
