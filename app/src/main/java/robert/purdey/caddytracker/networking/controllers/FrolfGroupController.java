package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.IdModel;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.domain.players.RemovePlayerModel;
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

        Call<EncryptModel> caller = getApiCall().getFrolfGroups(getAuthorizationHeader());

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<FrolfGroupModel> frolfGroups = Arrays.asList(decryptModel(response.body(), FrolfGroupModel[].class));
                    data.setValue(frolfGroups);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public MutableLiveData<FrolfGroupModel> getFrolfGroup(UUID id)
    {
        final MutableLiveData<FrolfGroupModel> data = new MutableLiveData<>();

        IdModel idModel              = new IdModel(id);
        EncryptModel encryptModel    = encryptModel(idModel);
        Call<EncryptModel> caller    = getApiCall().getById(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    FrolfGroupModel frolfGroupModel = decryptModel(response.body(), FrolfGroupModel.class);
                    data.setValue(frolfGroupModel);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public MutableLiveData<FrolfGroupModel> insert(FrolfGroupModel model)
    {
        final MutableLiveData<FrolfGroupModel> data = new MutableLiveData<>();

        EncryptModel encryptModel = encryptModel(model);
        Call<EncryptModel> caller = getApiCall().insert(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    FrolfGroupModel frolfGroupModel = decryptModel(response.body(), FrolfGroupModel.class);
                    data.setValue(frolfGroupModel);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });

        return data;
    }

    public void update(FrolfGroupModel model)
    {
        EncryptModel encryptModel = encryptModel(model);
        Call<Void> caller         = getApiCall().update(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if ( response.isSuccessful() )
                {

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                System.out.println("Failed to retrieve groups because you are a loser and have none!");
            }
        });
    }

    public MutableLiveData<List<PlayerModel>> getGroupMembers(UUID groupId)
    {
        final MutableLiveData<List<PlayerModel>> data = new MutableLiveData<>();

        IdModel idModel                = new IdModel(groupId);
        EncryptModel encryptModel      = encryptModel(idModel);
        Call<EncryptModel> caller = getApiCall().getGroupMembers(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<PlayerModel> groupMembers = Arrays.asList(decryptModel(response.body(), PlayerModel[].class));
                    data.setValue(groupMembers);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve group members because you are a loser and nobody wants to be in your group.");
            }
        });

        return data;
    }

    public MutableLiveData<GameModel> createGame(GameCreationModel model)
    {
        final MutableLiveData<GameModel> data = new MutableLiveData<>();

        EncryptModel encryptModel = encryptModel(model);
        Call<EncryptModel> caller = getApiCall().createGame(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    GameModel game = decryptModel(response.body(), GameModel.class);
                    data.setValue(game);
                }
                else
                {
                    System.out.println("loser");
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve group members because you are a loser and nobody wants to be in your group.");
            }
        });

        return data;
    }

    @Override
    public void leaveGroup(UUID groupId, IApiResponseListener listener)
    {
        IdModel idModel           = new IdModel(groupId);
        EncryptModel encryptModel = encryptModel(idModel);
        Call<Void> caller         = getApiCall().leaveGroup(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
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
            public void onFailure(Call<Void> call, Throwable t)
            {
                listener.onCallFailure();
            }
        });
    }

    @Override
    public void removePlayer(UUID groupId, UUID playerId, IApiResponseListener listener)
    {
        RemovePlayerModel removePlayerModel = new RemovePlayerModel(groupId, playerId);
        EncryptModel encryptModel           = encryptModel(removePlayerModel);
        Call<Void> caller                   = getApiCall().removePlayer(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
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
            public void onFailure(Call<Void> call, Throwable t)
            {
                listener.onCallFailure();
            }
        });
    }
}
