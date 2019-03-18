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
import robert.purdey.caddytracker.ui.adapters.GameListAdapter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.viewmodels.GameListViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameListFragment extends Fragment implements IItemClickListener
{
    private GameListViewModel gameListViewModel;
    private IItemClickListener gameClickLisetner;


    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_game_list, container, false);

        Context activityContext                    = getActivity();
        RecyclerView recyclerView                  = rootView.findViewById(R.id.gameListRecycleView);
        final GameListAdapter GameAdapter          = new GameListAdapter(activityContext, this);
        LinearLayoutManager layoutManger           = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(GameAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        gameListViewModel = ViewModelProviders.of(this).get(GameListViewModel.class);

        gameListViewModel.getGames().observe(this, GameModels ->
            GameAdapter.setGames(GameModels)
        );

        return rootView;
    }

    @Override
    public void onClick(View v, UUID id)
    {
        if ( gameClickLisetner != null ) {
            gameClickLisetner.onClick(v, id);
        }
    }

    public void SetGameClickListener(IItemClickListener listener)
    {
        gameClickLisetner = listener;
    }

}
