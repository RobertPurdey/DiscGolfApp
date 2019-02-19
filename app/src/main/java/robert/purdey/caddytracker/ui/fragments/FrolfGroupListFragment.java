package robert.purdey.caddytracker.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.FrolfGroupListAdapter;
import robert.purdey.caddytracker.ui.helpers.ActivityStarter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupViewModel;


public class FrolfGroupListFragment extends Fragment implements IItemClickListener
{
    private FrolfGroupViewModel frolfGroupViewModel;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_frolf_group_list, container, false);

        Context activityContext                    = getActivity();
        RecyclerView recyclerView                  = rootView.findViewById(R.id.frolfGroupRecycleView);
        final FrolfGroupListAdapter groupAdapter   = new FrolfGroupListAdapter(activityContext, this);
        LinearLayoutManager layoutManger           = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        frolfGroupViewModel = ViewModelProviders.of(this).get(FrolfGroupViewModel.class);

        frolfGroupViewModel.getFrolfGroups().observe(this, frolfGroupModels ->
            groupAdapter.setFrolfGroups(frolfGroupModels)
        );

        return rootView;
    }

    @Override
    public void onClick(View v, UUID id)
    {
        // todo: let the using activity set this!!
        ActivityStarter.startFrolfGroupRecordActivity(getActivity(), id);
    }

}
