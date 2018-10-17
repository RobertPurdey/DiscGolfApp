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
import robert.purdey.caddytracker.ui.adapters.FriendListAdapter;
import robert.purdey.caddytracker.ui.models.FriendModel;
import robert.purdey.caddytracker.ui.viewmodels.FriendsViewModel;

public class FriendListFragment extends Fragment
{
    private FriendsViewModel friends;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_friend_list, container, false);

        Context activityContext                = getActivity();
        RecyclerView recyclerView              = rootView.findViewById(R.id.friendListView);
        final FriendListAdapter friendAdapter  = new FriendListAdapter(activityContext);
        LinearLayoutManager layoutManger       = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(friendAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        friends = ViewModelProviders.of(this).get(FriendsViewModel.class);

        friends.getFriends().observe(this, new Observer<List<FriendModel>>() {
            @Override
            public void onChanged(@Nullable List<FriendModel> friendModels)
            {
                friendAdapter.setFriends(friendModels);
            }
        });

        return rootView;
    }
}
