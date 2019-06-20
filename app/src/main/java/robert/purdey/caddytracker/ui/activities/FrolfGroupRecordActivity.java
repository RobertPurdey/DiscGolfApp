package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityFrolfGroupRecordBinding;
import robert.purdey.caddytracker.ui.fragments.FrolfGroupMemberListFragment;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupRecordViewModel;
import robert.purdey.caddytracker.utilities.Strings;

public class FrolfGroupRecordActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    public static final String IS_NEW = "IS_NEW";
    private static @Nullable UUID FrolfGroupId;
    private FrolfGroupRecordViewModel frolfGroupRecordViewModel;

    public FrolfGroupRecordActivity()
    {

    }

    // todo: takes a model upon opening if its a new model (no id) its create, otherwise its an update (fetch data)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        createFrolfGroupRecordViewModel();

        ActivityFrolfGroupRecordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_frolf_group_record);

        binding.setFrolfGroupRecordViewModel(frolfGroupRecordViewModel);
        binding.setLifecycleOwner(this);

        // Attempt to get id. If given this is for an existing record
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(FrolfGroupRecordActivity.RECORD_ID);
        String isNew    = intent.getStringExtra(FrolfGroupRecordActivity.IS_NEW);

        if ( (recordId != null && !recordId.equals("") ) )
        {

            // get record data when set
            if ( !recordId.equals("") ) {
                UUID rId = UUID.fromString(recordId);
                FrolfGroupId = rId;
                frolfGroupRecordViewModel.getFrolfGroup(rId).observe(this, frolfGroupModel -> {
                    frolfGroupRecordViewModel.setFrolfGroupRecord(frolfGroupModel);
                    LoadMembers(frolfGroupModel.getIdKey());
                });
            }
        }
        else if ( Strings.isNullOrEmpty(isNew) )
        {
            if (FrolfGroupId != null)
            {
                frolfGroupRecordViewModel.getFrolfGroup(FrolfGroupId).observe(this, frolfGroupModel -> {
                    frolfGroupRecordViewModel.setFrolfGroupRecord(frolfGroupModel);
                    LoadMembers(frolfGroupModel.getIdKey());
                });
            }
        }

        getFrolfGroupMemberFrag().setShowSelection(false);
    }

    private void LoadMembers(UUID groupId)
    {
        FrolfGroupMemberListFragment fragment = getFrolfGroupMemberFrag();

        fragment.Load(groupId);
        fragment.setMemberClickListener( (view, id) -> confirmRemovePlayer(id) );
    }

    private FrolfGroupMemberListFragment getFrolfGroupMemberFrag()
    {
        return (FrolfGroupMemberListFragment) getSupportFragmentManager().findFragmentById(R.id.frag_frolf_group_member_list);
    }

    public void onCreateFrolfGroup(View view)
    {
        if ( frolfGroupRecordViewModel.groupId.getValue() == null )
        {
            frolfGroupRecordViewModel.insert().observe(this, frolfGroupModel -> {
                frolfGroupRecordViewModel.setFrolfGroupRecord(frolfGroupModel);
                LoadMembers(frolfGroupModel.getIdKey());
            });
        }
    }

    public void onSendInvite(View view)
    {
        // todo: use IApiResponseListener to inform when its sent
        frolfGroupRecordViewModel.sendGroupInvite();
    }

    public void onLeaveGroup(View view)
    {
        if ( frolfGroupRecordViewModel.groupId.getValue() != null )
        {
            frolfGroupRecordViewModel.leaveGroup(new IApiResponseListener()
            {
                @Override
                public void onResponseSuccessful()
                {
                    goToManageFrolfGroupsActivity();
                }

                @Override
                public void onResponseFailed()
                {
                    // todo toast message
                }

                @Override
                public void onCallFailure()
                {
                    // todo toast message
                }
            });
        }
    }

    private void removePlayer(UUID id)
    {
        frolfGroupRecordViewModel.removePlayer(id, new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                // Reload members
                LoadMembers(FrolfGroupId);
            }

            @Override
            public void onResponseFailed()
            {
                // todo:
            }

            @Override
            public void onCallFailure()
            {
                // todo:
            }
        });
    }

    private void confirmRemovePlayer(UUID playerId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder
            .setMessage("Are you sure you want to remove this player? The player's group data cannot be recovered.")
            .setPositiveButton("Yes", (dialog, id) -> removePlayer(playerId))
            .setNegativeButton("No",  (dialog, id) -> dialog.cancel() )
            .show();
    }

    private void goToManageFrolfGroupsActivity()
    {
        ActivityStarter.startManageFrolfGroupsActivity(this);
    }


    public void onManageCourses(View view)
    {
        if ( frolfGroupRecordViewModel.groupId.getValue() != null )
        {
            ActivityStarter.startManageCoursesActivity(
                this,
                UUID.fromString(frolfGroupRecordViewModel.groupId.getValue()));
        }
    }

    private void createFrolfGroupRecordViewModel()
    {
        frolfGroupRecordViewModel = ViewModelProviders.of(this).get(FrolfGroupRecordViewModel.class);
    }
}
