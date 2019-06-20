package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.contracts.calls.IFrolfGroupCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.models.GameCreationModel;
import robert.purdey.caddytracker.ui.models.GameModel;
import robert.purdey.caddytracker.ui.models.PlayerModel;

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
        final MutableLiveData<List<FrolfGroupModel>> data = new MutableLiveData<>();
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

    public MutableLiveData<FrolfGroupModel> getFrolfGroup(UUID id)
    {
        final MutableLiveData<FrolfGroupModel> data = new MutableLiveData<>();
        Call<FrolfGroupModel> caller = getApiCall().getById(getAuthorizationHeader(), id);

        caller.enqueue(new Callback<FrolfGroupModel>() {
            @Override
            public void onResponse(
                Call<FrolfGroupModel> call,
                Response<FrolfGroupModel> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<FrolfGroupModel> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public MutableLiveData<FrolfGroupModel> insert(FrolfGroupModel model)
    {
        final MutableLiveData<FrolfGroupModel> data = new MutableLiveData<>();
        Call<FrolfGroupModel> caller = getApiCall().insert(getAuthorizationHeader(), model);

        caller.enqueue(new Callback<FrolfGroupModel>() {
            @Override
            public void onResponse(
                Call<FrolfGroupModel> call,
                Response<FrolfGroupModel> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<FrolfGroupModel> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public MutableLiveData<FrolfGroupModel> update(FrolfGroupModel model)
    {
        final MutableLiveData<FrolfGroupModel> data = new MutableLiveData<>();
        Call<FrolfGroupModel> caller = getApiCall().update(getAuthorizationHeader(), model);

        caller.enqueue(new Callback<FrolfGroupModel>() {
            @Override
            public void onResponse(
                Call<FrolfGroupModel> call,
                Response<FrolfGroupModel> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<FrolfGroupModel> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public MutableLiveData<List<PlayerModel>> getGroupMembers(UUID groupId)
    {
        final MutableLiveData<List<PlayerModel>> data = new MutableLiveData<>();
        Call<List<PlayerModel>> caller = getApiCall().getGroupMembers(getAuthorizationHeader(), groupId);

        caller.enqueue(new Callback<List<PlayerModel>>() {
            @Override
            public void onResponse(
                Call<List<PlayerModel>> call,
                Response<List<PlayerModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<List<PlayerModel>> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve group members because you are a loser and nobody wants to be in your group.");
            }
        });

        return data;
    }

    public MutableLiveData<GameModel> createGame(GameCreationModel model)
    {
        final MutableLiveData<GameModel> data = new MutableLiveData<>();
        Call<GameModel> caller = getApiCall().createGame(getAuthorizationHeader(), model);

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
                else
                {
                    System.out.println("loser");
                }
            }

            @Override
            public void onFailure(
                Call<GameModel> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve group members because you are a loser and nobody wants to be in your group.");
            }
        });

        return data;
    }

    @Override
    public void leaveGroup(UUID groupId, IApiResponseListener listener)
    {
        Call<Void> caller = getApiCall().leaveGroup(getAuthorizationHeader(), groupId);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(
                Call<Void> call,
                Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    listener.onResponseSuccessful();
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
            }
        });
    }

    @Override
    public void removePlayer(UUID groupId, UUID playerId, IApiResponseListener listener)
    {
        Call<Void> caller = getApiCall().removePlayer(getAuthorizationHeader(), groupId, playerId);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(
                Call<Void> call,
                Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    listener.onResponseSuccessful();
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
            }
        });
    }
}
