package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.contracts.calls.IGameCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IGameController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;


public class GameController extends ApiController<IGameCall>
    implements IGameController
{
    public GameController(
        IApiCallService apiCallService)
    {
        super(apiCallService, IGameCall.class);
    }

    @Override
    public MutableLiveData<List<GameModel>> getAll()
    {
        final MutableLiveData<List<GameModel>> data = new MutableLiveData<>();
        Call<List<GameModel>> caller = getApiCall().getAll(getAuthorizationHeader());

        caller.enqueue(new Callback<List<GameModel>>() {
            @Override
            public void onResponse(
                Call<List<GameModel>> call,
                Response<List<GameModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<List<GameModel>> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve group invites because you are a loser and have none!");
            }
        });

        return data;
    }


    public MutableLiveData<GameModel> getGame(UUID id)
    {
        final MutableLiveData<GameModel> data = new MutableLiveData<>();
        Call<GameModel> caller = getApiCall().getById(getAuthorizationHeader(), id);

        caller.enqueue(new Callback<GameModel>() {
            @Override
            public void onResponse(
                Call<GameModel> call,
                Response<GameModel> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<GameModel> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public void updateGameHoles(GameHoleUpdateModel model, IApiResponseListener listener)
    {
        Call<Void> caller = getApiCall().updateGameHoles(getAuthorizationHeader(), model);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(
                Call<Void> call,
                Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    listener.onResponseSuccessful();
                    // todo: success callback
                }
                else
                {
                    listener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(
                Call<Void> call,
                Throwable t)
            {
                listener.onCallFailure();
                System.out.println("Failed to retrieve group members because you are a loser and nobody wants to be in your group.");
            }
        });
    }
}
