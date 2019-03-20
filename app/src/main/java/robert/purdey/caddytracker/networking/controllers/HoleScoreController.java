package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.holescores.HoleScoreFilterModel;
import robert.purdey.caddytracker.networking.contracts.calls.IHoleScoreCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IHoleScoreController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;

public class HoleScoreController
    extends ApiController<IHoleScoreCall>
    implements IHoleScoreController
{
    public HoleScoreController(
        IApiCallService apiCallService)
    {
        super(apiCallService, IHoleScoreCall.class);
    }

    @Override
    public MutableLiveData<List<HoleScoreModel>> getWithFilter(HoleScoreFilterModel filter)
    {
        final MutableLiveData<List<HoleScoreModel>> data = new MutableLiveData<>();
        Call<List<HoleScoreModel>> caller = getApiCall().getWithFilter(getAuthorizationHeader(), filter);

        caller.enqueue(new Callback<List<HoleScoreModel>>() {
            @Override
            public void onResponse(
                Call<List<HoleScoreModel>> call,
                Response<List<HoleScoreModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<List<HoleScoreModel>> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve hole scores because you are a loser and have none!");
            }
        });

        return data;
    }
}
