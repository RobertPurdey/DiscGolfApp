package robert.purdey.caddytracker.ui.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.FrolfGroupListAdapter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.viewmodels.FrolfGroupViewModel;


public class FrolfGroupListFragment extends Fragment implements IItemClickListener
{
    private FrolfGroupViewModel frolfGroupViewModel;
    private IItemClickListener groupClickedListener;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_frolf_group_list, container, false);

        Context activityContext                    = getActivity();
        RecyclerView recyclerView                  = rootView.findViewById(R.id.rcvw_frolf_group_recycle_view);
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
        if ( groupClickedListener != null ) {
            groupClickedListener.onClick(v, id);
        }
    }

    public void SetGroupClickListener(IItemClickListener listener)
    {
        groupClickedListener = listener;
    }

}
