package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitBuilder;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IRetrofitBuilder;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;

public class FrolfGroupViewModel extends ViewModel
{
    private MutableLiveData<List<FrolfGroupModel>> frolfGroups;

    public LiveData<List<FrolfGroupModel>> getFrolfGroups()
    {
        // todo: maybe this check requires isDirty??
        if (frolfGroups == null)
        {
            frolfGroups = new MutableLiveData<>();
            loadFrolfGroups();
        }

        return frolfGroups;
    }

    private void loadFrolfGroups()
    {
        // Todo: retro fit should be used in a repository class (view model shouldnt know how to make an API call)
        IRetrofitBuilder retrofitBuilder = new RetrofitBuilder(
            new RetrofitConfig(),
            new HttpClientConfig()
        );
        HashMap<String, String> clientHeaders = new HashMap<String, String>();
        clientHeaders.put(
            "Authorization",
            "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoicm9iIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJmZjhiNTNkNy1iNGZmLTQ5MjMtOTMxMS01N2RmM2M0YzMzNzUiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJhcHB1c2VyIiwibmJmIjoxNTQwMTcxODI5LCJleHAiOjE1NDAxNzM2Mjl9.pHNcFzVeGLdb3hfHFWRCDXX-l5Bs5SW8RIZF6R2Nvlg"
        );

        HttpClientArg clientArg = new HttpClientArg(
            10,
            10,
            IApiCall.BASE_URL,
            clientHeaders);

        Retrofit retrofit                   = retrofitBuilder.build(clientArg);
        IApiCall call                       = retrofit.create(IApiCall.class);
        Call<List<FrolfGroupModel>> caller  = call.getFrolfGroups();

        caller.enqueue(new Callback<List<FrolfGroupModel>>() {
            @Override
            public void onResponse(Call<List<FrolfGroupModel>> call, Response<List<FrolfGroupModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    frolfGroups.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<FrolfGroupModel>> call, Throwable t)
            {
                System.out.println("Failed to retrieve friends because you are a loser and have none!");
            }
        });
    }
}
