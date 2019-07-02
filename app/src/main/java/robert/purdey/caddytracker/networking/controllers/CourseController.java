package robert.purdey.caddytracker.networking.controllers;

import android.arch.lifecycle.MutableLiveData;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import robert.purdey.caddytracker.domain.IdModel;
import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
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

        Call<EncryptModel> caller = getApiCall().getAll(getAuthorizationHeader());

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {

                    List<CourseModel> courses = Arrays.asList(decryptModel(response.body(), CourseModel[].class));
                    data.setValue(courses);
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

    public MutableLiveData<CourseModel> getCourse(UUID id)
    {
        final MutableLiveData<CourseModel> data = new MutableLiveData<>();

        IdModel idModel             = new IdModel(id);
        EncryptModel encryptModel   = encryptModel(idModel);
        Call<EncryptModel> caller   = getApiCall().getById(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    CourseModel courseModel = decryptModel(response.body(), CourseModel.class);
                    data.setValue(courseModel);
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

    @Override
    public MutableLiveData<List<CourseModel>> getWithFilter(CourseFilter filter)
    {
        final MutableLiveData<List<CourseModel>> data = new MutableLiveData<>();

        EncryptModel encryptModel = encryptModel(filter);
        Call<EncryptModel> caller = getApiCall().getWithFilter(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    List<CourseModel> courses = Arrays.asList(decryptModel(response.body(), CourseModel[].class));
                    data.setValue(courses);
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

    public MutableLiveData<CourseModel> insert(CourseModel newModel)
    {
        final MutableLiveData<CourseModel> data = new MutableLiveData<>();

        EncryptModel encryptModel = encryptModel(newModel);
        Call<EncryptModel> caller = getApiCall().insert(getAuthorizationHeader(), encryptModel);

        caller.enqueue(new Callback<EncryptModel>() {
            @Override
            public void onResponse(Call<EncryptModel> call, Response<EncryptModel> response)
            {
                if ( response.isSuccessful() )
                {
                    CourseModel courseModel = decryptModel(response.body(), CourseModel.class);
                    data.setValue(courseModel);
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

    public void update(CourseModel newModel)
    {
        EncryptModel encryptModel   = encryptModel(newModel);
        Call<Void> caller           = getApiCall().update(getAuthorizationHeader(), encryptModel);

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

}

