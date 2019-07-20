package robert.purdey.caddytracker.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.evrencoskun.tableview.TableView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.ScoreCardViewAdapter;
import robert.purdey.caddytracker.ui.models.GameResultModel;
import robert.purdey.caddytracker.ui.models.scorecard.PlayerGameResultModel;
import robert.purdey.caddytracker.ui.models.scorecard.ScoreCellModel;
import robert.purdey.caddytracker.ui.models.scorecard.ScoreColumnHeaderModel;
import robert.purdey.caddytracker.ui.models.scorecard.ScoreRowHeaderModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreCardFragment extends Fragment
{
    private ScoreCardViewAdapter scoreCardTableAdapter;
    private TableView            scoreCardTableView;

    public ScoreCardFragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_score_card, container, false);

        scoreCardTableAdapter = new ScoreCardViewAdapter(getContext());
        scoreCardTableView    = rootView.findViewById(R.id.frag_score_card);

        scoreCardTableView.setAdapter(scoreCardTableAdapter);

        return rootView;
    }

    public void LoadScoreCard(GameResultModel result)
    {
        List<ScoreRowHeaderModel> rowHeaders        = new ArrayList<>();
        List<ScoreColumnHeaderModel> columnHeaders  = new ArrayList<>();
        List<List<ScoreCellModel>> playerResults    = new ArrayList<>();

        // order results by best score before displaying
        List<PlayerGameResultModel> playerResultModels = result.getPlayerResults();
        Collections.sort(playerResultModels, Comparator.comparingInt(PlayerGameResultModel::getTotalScore));

        // create columns
        columnHeaders.add(new ScoreColumnHeaderModel("Score"));

        // create hole columns headers
        for (int i = 1; i <= result.getHoleCount(); i++)
        {
            String header = "H" + Integer.toString(i);
            columnHeaders.add(new ScoreColumnHeaderModel(header));
        }

        columnHeaders.add(new ScoreColumnHeaderModel("Strokes"));

        // create row
        rowHeaders.add(new ScoreRowHeaderModel("PAR"));

        rowHeaders.addAll(playerResultModels
            .stream()
            .map(res -> new ScoreRowHeaderModel(res.getPlayerName()))
            .collect(Collectors.toList()));


        // to Par cells
        playerResults.add(ToParCells(result));

        for (PlayerGameResultModel model : playerResultModels)
        {
            playerResults.add(ToScoreCellModels(model));
        }

        scoreCardTableAdapter.setAllItems(columnHeaders, rowHeaders, playerResults);
    }

    private List<ScoreCellModel> ToParCells(GameResultModel model)
    {
        List<ScoreCellModel> cells = new ArrayList<>();
        cells.add(new ScoreCellModel("-"));

        for (int i = 1; i <= model.getHoleCount(); i++)
        {
            cells.add(new ScoreCellModel(model.getHolePars().get(i)));
        }

        cells.add(new ScoreCellModel("-"));

        return cells;
    }

    private List<ScoreCellModel> ToScoreCellModels(PlayerGameResultModel model)
    {
        List<ScoreCellModel> cells = new ArrayList<>();

        cells.add(new ScoreCellModel(model.getTotalScore()));

        // Scores / strokes
        Map<Integer, Integer> holeScores    = model.getScores();
        Map<Integer, Integer> holeStrokes   = model.getStrokes();
        int holeCount                       = holeStrokes.size();

        for(int i = 1; i <= holeCount; i++)
        {
            String cellValue = holeStrokes.get(i) + " (" + holeScores.get(i) + ")";
            cells.add(new ScoreCellModel(cellValue));
        }

        cells.add(new ScoreCellModel(model.getTotalStrokes()));

        return cells;
    }
}
