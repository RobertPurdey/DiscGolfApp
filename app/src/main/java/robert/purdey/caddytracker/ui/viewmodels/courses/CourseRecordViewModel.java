package robert.purdey.caddytracker.ui.viewmodels.courses;

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
import robert.purdey.caddytracker.ui.models.holes.HoleModel;

public class CourseRecordViewModel extends ViewModel
{
    public MutableLiveData<String> courseId;
    public MutableLiveData<String> frolfGroupId;

    public MutableLiveData<String> courseName;

    private MutableLiveData<CourseModel> course;
    private ICourseController courseController;

    public CourseRecordViewModel()
    {
        courseId        = new MutableLiveData<>();
        frolfGroupId    = new MutableLiveData<>();
        courseName      = new MutableLiveData<>();

        // todo: inject the following when possible
        ApiCallService apiCallService = new ApiCallService(
            new RetrofitConfig(),
            new HttpClientConfig()
        );

        courseController = new CourseController(apiCallService);
    }

    public LiveData<CourseModel> getCourse(UUID id)
    {
        // todo: maybe this check requires isDirty?? -- do i need this null check? do i care?
        //if (frolfGroup == null)
        //{
        course = new MutableLiveData<>();
        loadCourse(id);
        //}

        return course;
    }

    public LiveData<CourseModel> insert(List<HoleModel> courseHoles)
    {
        CourseModel model = new CourseModel();

        model.setFrolfGroupId(UUID.fromString(frolfGroupId.getValue()));
        model.setName(courseName.getValue());

        model.setHoles(courseHoles);

        course = courseController.insert(model);

        return course;
    }

    public void update()
    {
        CourseModel model = new CourseModel();

        model.setFrolfGroupId(UUID.fromString(frolfGroupId.getValue()));
        model.setIdKey(UUID.fromString(courseId.getValue()));
        model.setName(courseName.getValue());

        courseController.update(model);
    }

    public void setCourseRecord(CourseModel model)
    {
        courseId.setValue( model.getIdKey() == null ? null : model.getIdKey().toString() );
        frolfGroupId.setValue( model.getFrolfGroupId().toString() );
        courseName.setValue( model.getName() );
    }

    public HoleModel getNewHole(int tee)
    {
        UUID courseKey = UUID.randomUUID();

        if ( courseId.getValue() != null )
        {
            courseKey = UUID.fromString(courseId.getValue());
        }

        HoleModel newHole = new HoleModel();
        newHole.setOrder(tee);
        newHole.setPar(3);
        newHole.setCourseId(courseKey);
        newHole.setIdKey(UUID.randomUUID());

        return newHole;
    }

    private void loadCourse(UUID id)
    {
        course = courseController.getCourse(id);
    }
}
