package robert.purdey.caddytracker.ui.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.networking.HttpClientConfig;
import robert.purdey.caddytracker.networking.RetrofitConfig;
import robert.purdey.caddytracker.networking.contracts.controllers.ICourseController;
import robert.purdey.caddytracker.networking.controllers.CourseController;
import robert.purdey.caddytracker.networking.services.ApiCallService;
import robert.purdey.caddytracker.ui.models.CourseModel;

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

    public LiveData<List<CourseModel>> getCourses(CourseFilter filter)
    {
        courses = new MutableLiveData<>();
        loadCourses(filter);

        return courses;
    }

    private void loadCourses(CourseFilter filter)
    {
        courses = courseGroupController.getWithFilter(filter);
    }
}
