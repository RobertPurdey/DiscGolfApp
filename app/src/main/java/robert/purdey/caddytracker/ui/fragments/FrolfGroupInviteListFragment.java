package robert.purdey.caddytracker.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.FrolfGroupInviteListAdapter;
import robert.purdey.caddytracker.ui.helpers.Toaster;
import robert.purdey.caddytracker.ui.listeners.IApiResponseListener;
import robert.purdey.caddytracker.ui.listeners.IInviteActionClickListener;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupInviteViewModel;


public class FrolfGroupInviteListFragment extends Fragment implements IInviteActionClickListener
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

        getInvites();

        return rootView;
    }

    @Override
    public void onClickAccept(UUID inviteId)
    {
        frolfGroupInviteViewModel.acceptGroupInvite(inviteId, new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                inviteAcceptSuccess();
                frolfGroupInviteViewModel.resetInviteData();
                getInvites();
            }

            @Override
            public void onResponseFailed()
            {
                inviteAcceptFailure();
            }

            @Override
            public void onCallFailure()
            {

            }
        });
    }

    @Override
    public void onClickDecline(UUID inviteId)
    {
        frolfGroupInviteViewModel.declineGroupInvite(inviteId, new IApiResponseListener()
        {
            @Override
            public void onResponseSuccessful()
            {
                inviteDeclineSuccess();
                frolfGroupInviteViewModel.resetInviteData();
                getInvites();
            }

            @Override
            public void onResponseFailed()
            {
                inviteDeclineFailure();
            }

            @Override
            public void onCallFailure()
            {

            }
        });
    }

    private void inviteAcceptSuccess()
    {
        Toaster.quickSuccessToast(
            getContext(),
            R.string.invite_accepted_success_msg,
            Toast.LENGTH_SHORT);
    }


    private void inviteAcceptFailure()
    {
        Toaster.quickFailureToast(
            getContext(),
            R.string.invite_accepted_failure_msg,
            Toast.LENGTH_SHORT);
    }

    private void inviteDeclineSuccess()
    {
        Toaster.quickSuccessToast(
            getContext(),
            R.string.invite_decline_success_msg,
            Toast.LENGTH_SHORT);
    }

    private void inviteDeclineFailure()
    {
        Toaster.quickFailureToast(
            getContext(),
            R.string.invite_decline_failure_msg,
            Toast.LENGTH_SHORT);
    }

    private void getInvites()
    {
        frolfGroupInviteViewModel.getFrolfGroupInvites().observe(
            this,
            (invites) -> groupInviteAdapter.setFrolfGroupInvites(invites) );
    }
}
