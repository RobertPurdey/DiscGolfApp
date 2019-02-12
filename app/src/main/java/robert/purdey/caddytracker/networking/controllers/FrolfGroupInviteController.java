package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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
        Call<List<FrolfGroupInviteModel>> caller = getApiCall().getAll(getAuthorizationHeader());

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

    @Override
    public MutableLiveData<List<FrolfGroupInviteModel>> getWithFilter(FrolfGroupInviteFilterModel filter)
    {
        final MutableLiveData<List<FrolfGroupInviteModel>> data  = new MutableLiveData<>();
        Call<List<FrolfGroupInviteModel>> caller = getApiCall().getWithFilter(getAuthorizationHeader(), filter);

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

    // todo: should use a result model to return details about call?
    @Override
    public void accept(UUID inviteId, IApiResponseListener responseListener)
    {
        Call<Void> caller = getApiCall().accept(getAuthorizationHeader(), inviteId);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(
                Call<Void> call,
                Response<Void> response)
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
            public void onFailure(
                Call<Void> call,
                Throwable t)
            {
                responseListener.onCallFailure();
            }
        });
    }

    // todo: should use a result model to return details about call?
    @Override
    public void remove(UUID inviteId, IApiResponseListener responseListener)
    {
        Call<Void> caller = getApiCall().remove(getAuthorizationHeader(), inviteId);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(
                Call<Void> call,
                Response<Void> response)
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
            public void onFailure(
                Call<Void> call,
                Throwable t)
            {
                responseListener.onCallFailure();
            }
        });
    }

    @Override
    public void send(InviteCreationModel creationModel)
    {
        Call<Void> caller = getApiCall().send(getAuthorizationHeader(), creationModel);

        caller.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(
                Call<Void> call,
                Response<Void> response)
            {
                if ( response.isSuccessful() )
                {
                    // todo: what do i do???
                }
                else
                {
                    // todo: what do i do???
                }
            }

            @Override
            public void onFailure(
                Call<Void> call,
                Throwable t)
            {
                // todo: what do i do???
            }
        });
    }
}
