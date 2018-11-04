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
import robert.purdey.caddytracker.ui.adapters.FrolfGroupInviteListAdapter;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupInviteViewModel;


public class FrolfGroupInviteListFragment extends Fragment
{
    private FrolfGroupInviteViewModel frolfGroupInviteViewModel;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_frolf_group_invite_list, container, false);

        Context activityContext                               = getActivity();
        RecyclerView recyclerView                             = rootView.findViewById(R.id.frolfGroupInviteRecycleView);
        final FrolfGroupInviteListAdapter groupInviteAdapter  = new FrolfGroupInviteListAdapter(activityContext);
        LinearLayoutManager layoutManger                      = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(groupInviteAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        frolfGroupInviteViewModel = ViewModelProviders.of(this).get(FrolfGroupInviteViewModel.class);

        frolfGroupInviteViewModel.getFrolfGroupInvites().observe(this, new Observer<List<FrolfGroupInviteModel>>() {
            @Override
            public void onChanged(@Nullable List<FrolfGroupInviteModel> frolfGroupInviteModels)
            {
                groupInviteAdapter.setFrolfGroupInvites(frolfGroupInviteModels);
            }
        });

        return rootView;
    }
}
