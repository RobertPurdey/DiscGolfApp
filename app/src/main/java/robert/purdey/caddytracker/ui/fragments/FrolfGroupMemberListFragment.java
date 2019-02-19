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
import robert.purdey.caddytracker.ui.adapters.PlayerListAdapter;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupMembersViewModel;


public class FrolfGroupMemberListFragment  extends Fragment //todo: implement this click => implements IItemClickListener
{
    private PlayerListAdapter playerAdapter;
    private FrolfGroupMembersViewModel frolfGroupMembersViewModel;

    public FrolfGroupMemberListFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_frolf_group_member_list, container, false);

        Context activityContext                    = getActivity();
        RecyclerView recyclerView                  = rootView.findViewById(R.id.frolfGroupMembersRecycleView);
        playerAdapter                              = new PlayerListAdapter(activityContext);//todo: click list imp, this);
        LinearLayoutManager layoutManger           = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(playerAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        frolfGroupMembersViewModel = ViewModelProviders.of(this).get(FrolfGroupMembersViewModel.class);

        return rootView;
    }

    public void Load(UUID groupId)
    {
        frolfGroupMembersViewModel.getFrolfGroupMembers(groupId).observe(this, playerModels ->
            playerAdapter.setPlayers(playerModels)
        );
    }
}
