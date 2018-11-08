package robert.purdey.caddytracker.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.UUID;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.FrolfGroupInviteListAdapter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupInviteViewModel;


public class FrolfGroupInviteListFragment extends Fragment implements IItemClickListener
{
    private FrolfGroupInviteViewModel frolfGroupInviteViewModel;
    private FrolfGroupInviteListAdapter groupInviteAdapter;

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_frolf_group_invite_list, container, false);

        Context activityContext            = getActivity();
        RecyclerView recyclerView          = rootView.findViewById(R.id.frolfGroupInviteRecycleView);
        groupInviteAdapter                 = new FrolfGroupInviteListAdapter(activityContext, this);
        LinearLayoutManager layoutManger   = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(groupInviteAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        frolfGroupInviteViewModel = ViewModelProviders.of(this).get(FrolfGroupInviteViewModel.class);

        GetInvites();

        return rootView;
    }

    @Override
    public void onClick(View view, UUID inviteId)
    {
        frolfGroupInviteViewModel.acceptGroupInvite(inviteId, this::GetInvites);
    }

    public void GetInvites()
    {
        frolfGroupInviteViewModel.getFrolfGroupInvites().observe(
            this,
            (invites) -> groupInviteAdapter.setFrolfGroupInvites(invites) );
    }
}
