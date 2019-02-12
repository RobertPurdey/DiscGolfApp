package robert.purdey.caddytracker.ui.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.databinding.ActivityFrolfGroupRecordBinding;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupRecordViewModel;

public class FrolfGroupRecordActivity extends AppCompatActivity
{
    public static final String RECORD_ID = "RECORD_ID";
    private FrolfGroupRecordViewModel frolfGroupRecordViewModel;

    public FrolfGroupRecordActivity()
    {

    }

    // todo: takes a model upon opening if its a new model (no id) its create, otherwise its an update (fetch data)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frolf_group_record);

        createFrolfGroupRecordViewModel();

        ActivityFrolfGroupRecordBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_frolf_group_record);

        binding.setFrolfGroupRecordViewModel(frolfGroupRecordViewModel);
        binding.setLifecycleOwner(this);

        // Attempt to get id. If given this is for an existing record
        Intent intent   = getIntent();
        String recordId = intent.getStringExtra(FrolfGroupRecordActivity.RECORD_ID);


        // get record data when set
        if ( !recordId.equals("") ) {
            UUID rId = UUID.fromString(recordId);
            frolfGroupRecordViewModel.getFrolfGroup(rId).observe(this, frolfGroupModel ->
                frolfGroupRecordViewModel.setFrolfGroupRecord(frolfGroupModel)
            );
        }
    }

    public void onCreateFrolfGroup(View view)
    {
        // todo: use IApiResponseListener
        frolfGroupRecordViewModel.insert();
    }

    private void createFrolfGroupRecordViewModel()
    {
        frolfGroupRecordViewModel = ViewModelProviders.of(this).get(FrolfGroupRecordViewModel.class);
    }
}
