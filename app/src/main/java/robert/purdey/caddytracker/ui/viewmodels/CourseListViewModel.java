package robert.purdey.caddytracker.ui.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.ICourseController;
import robert.purdey.caddytracker.networking.controllers.CourseController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.CourseModel;
import robert.purdey.caddytracker.ui.models.PlayerModel;

public class CourseListViewModel extends ViewModel
{
    private MutableLiveData<List<CourseModel>> courses;
    private ICourseController courseGroupController;

    public CourseListViewModel()
    {
        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        courseGroupController = new CourseController(apiCallService);
    }

    public LiveData<List<CourseModel>> getCourses()
    {
        // todo: maybe this check requires isDirty??
        if (courses == null)
        {
            courses = new MutableLiveData<>();
            loadCourses();
        }

        return courses;
    }

    private void loadCourses()
    {
        courses = courseGroupController.getAll();
    }
}
