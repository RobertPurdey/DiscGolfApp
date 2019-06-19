package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityCourseRecordBinding;
import robert.purdey.caddytracker.ui.fragments.CourseHolesFragment;
import robert.purdey.caddytracker.ui.models.CourseModel;
import robert.purdey.caddytracker.ui.models.holes.HoleModel;
import robert.purdey.caddytracker.ui.viewmodels.courses.CourseRecordViewModel;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CourseRecordActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    public static final String FROLF_GROUP_ID = "FROLF_GROUP_ID";

    private CourseRecordViewModel courseRecordViewModel;

    public CourseRecordActivity()
    {

    }

    // todo: takes a model upon opening if its a new model (no id) its create, otherwise its an update (fetch data)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        createFrolfGroupRecordViewModel();
        ActivityCourseRecordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_course_record);

        binding.setCourseRecordViewModel(courseRecordViewModel);
        binding.setLifecycleOwner(this);

        // Attempt to get id. If given, this is for an existing record
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(CourseRecordActivity.RECORD_ID);

        // Attempt to get frolf group id. If given for record that is new good, otherwise bad
        String frolfGroupId = intent.getStringExtra(CourseRecordActivity.FROLF_GROUP_ID);

        // get record data when set (existing record)
        if ( !recordId.equals("") ) {
            UUID rId = UUID.fromString(recordId);
            courseRecordViewModel.getCourse(rId).observe(this, courseModel -> {
                courseRecordViewModel.setCourseRecord(courseModel);
                LoadHoles(courseModel.getHoles());
            });
        }
        // new record
        else if ( !frolfGroupId.equals("") )
        {
            UUID id                 = UUID.fromString(frolfGroupId);
            CourseModel newModel    = CreateNewCourseModel(id);

            courseRecordViewModel.setCourseRecord(newModel);
            LoadHoles(newModel.getHoles());
        }
    }

    private CourseModel CreateNewCourseModel(UUID frolfGroupId)
    {
        CourseModel newModel = new CourseModel();

        newModel.setName("");
        newModel.setFrolfGroupId( frolfGroupId );
        newModel.setPar( 27 );
        newModel.setHoles( CreateNewHoles(9) );
        newModel.setIdKey(null);

        return newModel;
    }

    private List<HoleModel> CreateNewHoles(int n)
    {
        ArrayList<HoleModel> newHoles = new ArrayList<>();

        for (int i = 1; i <= n; i++)
        {
            HoleModel newModel = new HoleModel();
            newModel.setOrder(i);
            newModel.setPar(3);

            newModel.setIdKey(UUID.randomUUID());
            newModel.setCourseId(UUID.randomUUID());

            newHoles.add(newModel);
        }

        return newHoles;
    }

    private void LoadHoles(List<HoleModel> courseHoles)
    {
        getCourseHoleFrag().Load(courseHoles);
    }

    private CourseHolesFragment getCourseHoleFrag()
    {
        return (CourseHolesFragment)getSupportFragmentManager().findFragmentById(R.id.frag_course_record_course_holes);
    }

    public void onCreateCourse(View view)
    {
        if ( courseRecordViewModel.courseId.getValue() == null )
        {
            List<HoleModel> courseHoles = getCourseHoleFrag().getCourseHoles();

            courseRecordViewModel.insert(courseHoles).observe(this, courseModel -> {
                courseRecordViewModel.setCourseRecord(courseModel);
                LoadHoles(courseModel.Holes);
            });
        }
    }

    public void onAddHole(View view)
    {
        CourseHolesFragment holeFrag    = getCourseHoleFrag();
        int nextTee                     = getCourseHoleFrag().getCourseHoles().size()+1;
        HoleModel newHole               = courseRecordViewModel.getNewHole(nextTee);

        holeFrag.addHole(newHole);
    }

    public void onRemoveHole(View view)
    {
        CourseHolesFragment holeFrag = getCourseHoleFrag();

        if ( holeFrag.getCourseHoles().size() > 1 )
        {
            holeFrag.removeHole();
        }
    }

    private void createFrolfGroupRecordViewModel()
    {
        courseRecordViewModel = ViewModelProviders.of(this).get(CourseRecordViewModel.class);
    }
}
