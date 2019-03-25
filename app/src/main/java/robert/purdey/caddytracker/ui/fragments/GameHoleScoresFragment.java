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

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.GameHoleScoresAdapter;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;
import robert.purdey.caddytracker.ui.viewmodels.GameHoleScoresViewModel;


public class GameHoleScoresFragment extends Fragment
{
    private GameHoleScoresAdapter gameHoleScoreAdapter;
    private GameHoleScoresViewModel gameHoleScoresViewModel;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_game_hole_scores, container, false);

        Context activityContext                   = getActivity();
        RecyclerView recyclerView                 = rootView.findViewById(R.id.gameHoleScoresRecycleView);
        gameHoleScoreAdapter                      = new GameHoleScoresAdapter(activityContext);
        LinearLayoutManager layoutManger          = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(gameHoleScoreAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        gameHoleScoresViewModel = ViewModelProviders.of(this).get(GameHoleScoresViewModel.class);

        return rootView;
    }

    public void Load(UUID gameId, int holeNumber)
    {
        gameHoleScoresViewModel.GameId.setValue(gameId);

        gameHoleScoresViewModel.getHoleScores(gameId, holeNumber, true).observe(this, holeScoreModels ->
            gameHoleScoreAdapter.setGameHoleScores(holeScoreModels)
        );
    }

    public List<HoleScoreModel> getHoleScores()
    {
        return gameHoleScoreAdapter.getGameHoleScores();
    }
}
