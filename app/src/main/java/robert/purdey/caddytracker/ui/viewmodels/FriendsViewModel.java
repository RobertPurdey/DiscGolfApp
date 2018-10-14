package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import robert.purdey.caddytracker.networking.ApiCallBuilder;
import robert.purdey.caddytracker.networking.ApiCallManager;
import robert.purdey.caddytracker.networking.HttpClientBuilder;
import robert.purdey.caddytracker.networking.RetrofitBuilder;
import robert.purdey.caddytracker.networking.arguments.HttpClientArg;
import robert.purdey.caddytracker.networking.contracts.IApiCall;
import robert.purdey.caddytracker.networking.contracts.IApiCallManager;
import robert.purdey.caddytracker.services.ApiCall;
import robert.purdey.caddytracker.ui.models.FriendModel;

/**
 * Represents an application user
 */
public class FriendsViewModel extends ViewModel
{
    private MutableLiveData<List<FriendModel>> friends;

    public LiveData<List<FriendModel>> getFriends()
    {
        // todo: maybe this check requires isDirty??
        if (friends == null)
        {
            friends = new MutableLiveData<>();

            loadFriends();
        }

        return friends;
    }

    private void loadFriends()
    {
        IApiCallManager apiManager = new ApiCallManager(
            new ApiCallBuilder(),
            new RetrofitBuilder(),
            new HttpClientBuilder()
        );
        HashMap<String, String> clientHeaders = new HashMap<String, String>();
        clientHeaders.put(
            "Authorization",
            "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoicm9iIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJmZjhiNTNkNy1iNGZmLTQ5MjMtOTMxMS01N2RmM2M0YzMzNzUiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJhcHB1c2VyIiwibmJmIjoxNTM5NDc3ODIwLCJleHAiOjE1Mzk0Nzk2MjB9.CJPxpaGDc9WKkTYtYPSDvOag2-cYbtmlmRSsjFtGPEk"
        );

        HttpClientArg clientArg = new HttpClientArg(
            10,
            10,
            "",
            clientHeaders);

        IApiCall call                    = apiManager.build(clientArg);
        Call<List<FriendModel>> caller   = call.getFriends();

        caller.enqueue(new Callback<List<FriendModel>>() {
            @Override
            public void onResponse(Call<List<FriendModel>> call, Response<List<FriendModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    friends.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<FriendModel>> call, Throwable t)
            {
                System.out.println("Failed to retrieve friends because you are a loser and have none!");
            }
        });
    }
}