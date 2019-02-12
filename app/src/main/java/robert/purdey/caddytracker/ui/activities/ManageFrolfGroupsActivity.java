package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;

public class ManageFrolfGroupsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_frolf_groups);
    }

    /**
     * Start FrolfGroupRecordActivity in create mode
     *
     * @param view - view calling the method
     */
    public void onClickNewGroup(View view)
    {
        // todo: pass create mode when ready
        ActivityStarter.startFrolfGroupRecordActivity(this, null);
    }
}
