package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.widget.Toast;

import java.io.IOException;
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
        // build client
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException
                {
                    Request request            = chain.request();
                    Request.Builder newRequest = request
                        .newBuilder()
                        .header("Authorization", "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoicm9iIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJmZjhiNTNkNy1iNGZmLTQ5MjMtOTMxMS01N2RmM2M0YzMzNzUiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJhcHB1c2VyIiwibmJmIjoxNTM5MDYyMzQ1LCJleHAiOjE1MzkwNjQxNDV9.wPi2ajXaeh0STp0NiLA2FWW3avfr-bA7mdsawQd__DI");

                    return chain.proceed(newRequest.build());
                }
            });

        // build retrofit
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(ApiCall.BASE_URL)
            .client(okHttpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        ApiCall call                     = retrofit.create(ApiCall.class);
        Call<List<FriendModel>> caller   = call.getFriends();

        caller.enqueue(new Callback<List<FriendModel>>() {
            @Override
            public void onResponse(Call<List<FriendModel>> call, Response<List<FriendModel>> response)
            {
                friends.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<FriendModel>> call, Throwable t)
            {
                System.out.println("Failed to retrieve friends because you are a loser and have none!");
            }
        });
        /*// todo: this needs to be an api call
        List<FriendModel> foundFriends = new ArrayList<FriendModel>()
        {{
            add(
                new FriendModel()
                {{
                    setIdKey(UUID.fromString("fab09466-a3e4-4d62-b77f-a974824cea9d"));
                    setNickName("Katie");
                }}
            );

            add(
                new FriendModel()
                {{
                    setIdKey(UUID.fromString("cdaa11e6-d3af-4865-8470-e10d9122e9b3"));
                    setNickName("Rob");
                }}
            );

        }};

        friends.setValue(foundFriends);
        */
    }
}

