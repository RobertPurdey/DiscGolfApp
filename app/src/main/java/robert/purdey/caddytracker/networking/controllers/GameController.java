package robert.purdey.caddytracker.networking.controllers;

import androidx.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.IdModel;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.domain.games.GameFilter;
import robert.purdey.caddytracker.networking.contracts.calls.IGameCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IGameController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.GameHoleUpdateModel;
import robert.purdey.caddytracker.ui.models.GameModel;
import robert.purdey.caddytracker.ui.models.GameResultModel;


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

        Call<EncryptModel> caller = getApiCall().getAll(getAuthorizationHeader());

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<GameModel> games = Arrays.asList(decryptModel(response.body(), GameModel[].class));
                    data.setValue(games);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve group invites because you are a loser and have none!");
            }
        });

        return data;
    }

    @Override
    public MutableLiveData<List<GameModel>> getWithFilter(GameFilter filter)
    {
        final MutableLiveData<List<GameModel>> data = new MutableLiveData<>();

        EncryptModel encryptModel = encryptModel(filter);
        Call<EncryptModel> caller = getApiCall().getWithFilter(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<GameModel> games = Arrays.asList(decryptModel(response.body(), GameModel[].class));
                    data.setValue(games);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve group invites because you are a loser and have none!");
            }
        });

        return data;
    }

    @Override
    public MutableLiveData<GameResultModel> getGameResults(UUID id)
    {
        final MutableLiveData<GameResultModel> data = new MutableLiveData<>();

        IdModel idModel              = new IdModel(id);
        EncryptModel encryptModel    = encryptModel(idModel);
        Call<EncryptModel> caller    = getApiCall().getGameResults(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    GameResultModel gameResult = decryptModel(response.body(), GameResultModel.class);
                    data.setValue(gameResult);
                }
            }

            @Override
            public void onFailure(Call<EncryptModel> call, Throwable t)
            {
                System.out.println("Failed to retrieve group invites because you are a loser and have none!");
            }
        });

        return data;
    }

    public MutableLiveData<GameModel> getGame(UUID id)
    {
        final MutableLiveData<GameModel> data = new MutableLiveData<>();

        IdModel idModel            = new IdModel(id);
        EncryptModel encryptModel  = encryptModel(idModel);
        Call<EncryptModel> caller  = getApiCall().getById(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    GameModel game = decryptModel(response.body(), GameModel.class);
                    data.setValue(game);
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

    public void updateGameHoles(GameHoleUpdateModel model, IApiResponseListener listener)
    {
        EncryptModel encryptModel = encryptModel(model);
        Call<Void> caller         = getApiCall().updateGameHoles(getAuthorizationHeader(), encryptModel);

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
                System.out.println("Failed to retrieve group members because you are a loser and nobody wants to be in your group.");
            }
        });
    }


    public void completeGame(UUID id, IApiResponseListener listener)
    {
        IdModel idModel             = new IdModel(id);
        EncryptModel encryptModel   = encryptModel(idModel);
        Call<Void> caller           = getApiCall().completeGame(getAuthorizationHeader(), encryptModel);

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
                System.out.println("Failed to complete game");
            }
        });
    }
}
