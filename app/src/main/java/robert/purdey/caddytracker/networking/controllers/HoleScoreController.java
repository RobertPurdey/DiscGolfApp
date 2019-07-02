package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
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

        EncryptModel encryptModel  = encryptModel(filter);
        Call<EncryptModel> caller  = getApiCall().getWithFilter(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<HoleScoreModel> holeScores = Arrays.asList(decryptModel(response.body(), HoleScoreModel[].class));
                    data.setValue(holeScores);
                    data.getValue().sort(Comparator.comparing(HoleScoreModel::getPlayerHandle));
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve hole scores because you are a loser and have none!");
            }
        });

        return data;
    }
}
