package robert.purdey.caddytracker.ui.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.FrolfGroupListAdapter;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupViewModel;


public class FrolfGroupListFragment extends Fragment
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
        final FrolfGroupListAdapter groupAdapter   = new FrolfGroupListAdapter(activityContext);
        LinearLayoutManager layoutManger           = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(groupAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        frolfGroupViewModel = ViewModelProviders.of(this).get(FrolfGroupViewModel.class);

        frolfGroupViewModel.getFrolfGroups().observe(this, new Observer<List<FrolfGroupModel>>() {
            @Override
            public void onChanged(@Nullable List<FrolfGroupModel> frolfGroupModels)
            {
                groupAdapter.setFrolfGroups(frolfGroupModels);
            }
        });

        return rootView;
    }
}
