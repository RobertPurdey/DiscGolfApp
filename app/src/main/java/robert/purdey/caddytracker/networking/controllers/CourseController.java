package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.networking.contracts.calls.ICourseCall;
import robert.purdey.caddytracker.networking.contracts.controllers.ICourseController;
import robert.purdey.caddytracker.networking.contracts.services.IApiCallService;
import robert.purdey.caddytracker.ui.models.CourseModel;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class CourseController extends ApiController<ICourseCall>
    implements ICourseController
{
    public CourseController(
        IApiCallService apiCallService)
    {
        super(apiCallService, ICourseCall.class);
    }

    @Override
    public MutableLiveData<List<CourseModel>> getAll()
    {
        final MutableLiveData<List<CourseModel>> data = new MutableLiveData<>();
        Call<List<CourseModel>> caller = getApiCall().getAll(getAuthorizationHeader());

        caller.enqueue(new Callback<List<CourseModel>>() {
            @Override
            public void onResponse(
                Call<List<CourseModel>> call,
                Response<List<CourseModel>> response)
            {
                if ( response.isSuccessful() )
                {
                    data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(
                Call<List<CourseModel>> call,
                Throwable t)
            {
                System.out.println("Failed to retrieve group invites because you are a loser and have none!");
            }
        });

        return data;
    }
}

