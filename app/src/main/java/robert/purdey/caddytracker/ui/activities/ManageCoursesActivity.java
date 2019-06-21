package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.ui.fragments.CourseListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

public class ManageCoursesActivity extends AppCompatActivity
{
    public static final String FROLF_GROUP_ID = "FROLF_GROUP_ID";

    public UUID FrolfGroupId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_courses);

        // Attempt to get frolf group id. If given for record that is new good, otherwise bad
        Intent intent       = getIntent();
        String frolfGroupId = intent.getStringExtra(ManageCoursesActivity.FROLF_GROUP_ID);

        // get frolf id managing courses is for
        if ( frolfGroupId != null && !frolfGroupId.equals("") )
        {
            UUID id         = UUID.fromString(frolfGroupId);
            FrolfGroupId    = id;
            loadHoles();
        }

        getCourseListFrag().setCourseClickListener( (view, id) -> onCourseSelected(id) );
    }

    private void loadHoles()
    {
        CourseFilter filter = new CourseFilter();
        filter.setFrolfGroupId(FrolfGroupId);

        getCourseListFrag().LoadCourses(filter);
    }

    private CourseListFragment getCourseListFrag()
    {
        return (CourseListFragment)getSupportFragmentManager().findFragmentById(R.id.frag_mng_courses_select_fragment);
    }

    private void onCourseSelected(UUID id)
    {
        ActivityStarter.startCourseRecordActivity(this, id, FrolfGroupId);
    }
    /**
     * Start FrolfGroupRecordActivity in create mode
     *
     * @param view - view calling the method
     */
    public void onClickNewCourse(View view)
    {
        // todo: pass create mode when ready
        ActivityStarter.startCourseRecordActivity(this, null, FrolfGroupId);
    }
}
