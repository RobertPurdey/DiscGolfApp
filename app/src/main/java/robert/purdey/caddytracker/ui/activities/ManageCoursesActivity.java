package robert.purdey.caddytracker.ui.activities;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.models.CourseModel;

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

        // new record
        if ( !frolfGroupId.equals("") )
        {
            UUID id         = UUID.fromString(frolfGroupId);
            FrolfGroupId    = id;
        }
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
