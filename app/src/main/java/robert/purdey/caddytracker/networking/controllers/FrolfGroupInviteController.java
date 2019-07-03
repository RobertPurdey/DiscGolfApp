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
import robert.purdey.caddytracker.domain.frolfgroups.FrolfGroupInviteFilterModel;
import robert.purdey.caddytracker.networking.contracts.calls.IFrolfGroupInviteCall;
import robert.purdey.caddytracker.networking.contracts.controllers.IFrolfGroupInviteController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;
import robert.purdey.caddytracker.ui.models.InviteCreationModel;

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
    public MutableLiveData<List<FrolfGroupInviteModel>> getAll()
    {
        final MutableLiveData<List<FrolfGroupInviteModel>> data  = new MutableLiveData<>();

        Call<EncryptModel> caller = getApiCall().getAll(getAuthorizationHeader());

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<FrolfGroupInviteModel> invites = Arrays.asList(decryptModel(response.body(), FrolfGroupInviteModel[].class));
                    data.setValue(invites);
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
    public MutableLiveData<List<FrolfGroupInviteModel>> getWithFilter(FrolfGroupInviteFilterModel filter)
    {
        final MutableLiveData<List<FrolfGroupInviteModel>> data  = new MutableLiveData<>();

        EncryptModel encryptModel = encryptModel(filter);
        Call<EncryptModel> caller = getApiCall().getWithFilter(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<FrolfGroupInviteModel> invites = Arrays.asList(decryptModel(response.body(), FrolfGroupInviteModel[].class));
                    data.setValue(invites);
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
    public void accept(UUID inviteId, IApiResponseListener responseListener)
    {
        IdModel idModel             = new IdModel(inviteId);
        EncryptModel encryptModel   = encryptModel(idModel);
        Call<Void> caller           = getApiCall().accept(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    responseListener.onResponseSuccessful();
                }
                else
                {
                    responseListener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                responseListener.onCallFailure();
            }
        });
    }

    @Override
    public void remove(UUID inviteId, IApiResponseListener responseListener)
    {
        IdModel idModel             = new IdModel(inviteId);
        EncryptModel encryptModel   = encryptModel(idModel);
        Call<Void> caller           = getApiCall().remove(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    responseListener.onResponseSuccessful();
                }
                else
                {
                    responseListener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                responseListener.onCallFailure();
            }
        });
    }

    @Override
    public void send(InviteCreationModel creationModel, IApiResponseListener responseListener)
    {
        EncryptModel encryptModel = encryptModel(creationModel);
        Call<Void> caller         = getApiCall().send(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    responseListener.onResponseSuccessful();
                }
                else
                {
                    responseListener.onResponseFailed();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t)
            {
                responseListener.onCallFailure();
            }
        });
    }
}
