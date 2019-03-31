package robert.purdey.caddytracker.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.evrencoskun.tableview.TableView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.ScoreCardViewAdapter;
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

    public void LoadScoreCard(List<PlayerGameResultModel> results)
    {
        List<ScoreRowHeaderModel> rowHeaders        = new ArrayList<>();
        List<ScoreColumnHeaderModel> columnHeaders  = new ArrayList<>();
        List<List<ScoreCellModel>> playerResults    = new ArrayList<>();

        columnHeaders.add(new ScoreColumnHeaderModel("Score"));
        columnHeaders.add(new ScoreColumnHeaderModel("Strokes"));

        int holeCount = results.get(0).getScores().size();

        for (int i = 1; i <= holeCount; i++)
        {
            columnHeaders.add(new ScoreColumnHeaderModel(Integer.toString(i)));
        }

        rowHeaders = results
            .stream()
            .map(res -> new ScoreRowHeaderModel(res.getPlayerName()))
            .collect(Collectors.toList());

        for (PlayerGameResultModel model : results)
        {
            playerResults.add(ToScoreCellModels(model));
        }

        scoreCardTableAdapter.setAllItems(columnHeaders, rowHeaders, playerResults);
    }

    private List<ScoreCellModel> ToScoreCellModels(PlayerGameResultModel model)
    {
        List<ScoreCellModel> cells = new ArrayList<>();

        cells.add(new ScoreCellModel(model.getTotalScore()));
        cells.add(new ScoreCellModel(model.getStrokes()));

        // Scores
        int holeCount                       = model.getScores().size();
        Map<Integer, Integer> holeScores    = model.getScores();

        for(int i = 1; i <= holeCount; i++)
        {
            cells.add(new ScoreCellModel(holeScores.get(i)));
        }

        return cells;
    }
}
