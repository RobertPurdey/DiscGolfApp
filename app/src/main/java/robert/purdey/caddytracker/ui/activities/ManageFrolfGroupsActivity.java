package robert.purdey.caddytracker.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.fragments.FrolfGroupListFragment;
import robert.purdey.caddytracker.ui.fragments.FrolfGroupMemberListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;

public class ManageFrolfGroupsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_frolf_groups);
        SetFragmentClick();
        // Set click to start frolf record
    }

    /**
     * Start FrolfGroupRecordActivity in create mode
     *
     * @param view - view calling the method
     */
    public void onClickNewGroup(View view)
    {
        // todo: pass create mode when ready
        ActivityStarter.startFrolfGroupRecordActivity(this, null, true);
    }

    private void SetFragmentClick()
    {
        FrolfGroupListFragment fragment = (FrolfGroupListFragment) getSupportFragmentManager()
            .findFragmentById(R.id.frag_mng_frolf_group_select_fragment);

        fragment.SetGroupClickListener( (view, id) ->
            ActivityStarter.startFrolfGroupRecordActivity(this, id, false)
        );
    }
}
