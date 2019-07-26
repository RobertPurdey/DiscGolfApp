package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;
import robert.purdey.caddytracker.utilities.Integers;


public class GameHoleScoresAdapter extends RecyclerView.Adapter<GameHoleScoresAdapter.GameHoleScoresViewHolder>
{

    private final LayoutInflater mInflater;
    // Cached copy of GameHoleScoress
    private List<HoleScoreModel> mGameHoleScores;

    public GameHoleScoresAdapter(Context context)
    {
        mInflater  = LayoutInflater.from(context);
    }

    @Override
    public GameHoleScoresAdapter.GameHoleScoresViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_game_hole_score,
            parent,
            false);

        return new GameHoleScoresAdapter.GameHoleScoresViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameHoleScoresAdapter.GameHoleScoresViewHolder holder, int position)
    {
        if (mGameHoleScores != null)
        {
            HoleScoreModel current = mGameHoleScores.get(position);

            holder.txtvHoleScoreId.setText(current.getIdKey().toString());
            holder.txtvPlayerName.setText(current.getPlayerHandle());
            holder.txtvScore.setText(Integers.SignInt(current.getScore()));
            holder.txtvStrokes.setText(Integer.toString(current.getStrokes()));
            holder.bttnDecrease.setEnabled(current.getStrokes() > 1 );
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvHoleScoreId.setText("");
            holder.txtvPlayerName.setText("");
            holder.txtvScore.setText("0");
            holder.txtvStrokes.setText("0");
        }
    }

    public void setGameHoleScores(List<HoleScoreModel> holeScores)
    {
        mGameHoleScores = holeScores;
        notifyDataSetChanged();
    }

    public List<HoleScoreModel> getGameHoleScores()
    {
        return mGameHoleScores;
    }

    // getItemCount() is called many times, and when it is first called,
    // mFriends has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount()
    {
        return mGameHoleScores != null
            ? mGameHoleScores.size()
            : 0;
    }

    class GameHoleScoresViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvHoleScoreId;
        private final TextView txtvPlayerName;
        private final TextView txtvScore;
        private final TextView txtvStrokes;
        private final Button bttnIncrease;
        private final Button bttnDecrease;

        private GameHoleScoresViewHolder(View itemView)
        {
            super(itemView);

            txtvHoleScoreId          = itemView.findViewById(R.id.txtv_row_item_game_hole_score_hole_score_id);
            txtvPlayerName           = itemView.findViewById(R.id.txtv_row_item_game_hole_score_player_name);
            txtvScore                = itemView.findViewById(R.id.txtv_row_item_game_hole_score_score);
            txtvStrokes              = itemView.findViewById(R.id.txtv_row_item_game_hole_score_strokes);
            bttnIncrease             = itemView.findViewById(R.id.bttn_row_item_game_hole_score_strokes_increase);
            bttnDecrease             = itemView.findViewById(R.id.bttn_row_item_game_hole_score_strokes_decrease);

            setIncreaseOnClick();
            setDecreaseOnClick();
        }

        private void setIncreaseOnClick()
        {
            bttnIncrease.setOnClickListener(view -> ModifyHoleScore(true) );
        }

        private void setDecreaseOnClick()
        {
            bttnDecrease.setOnClickListener(view -> ModifyHoleScore(false) );
        }

        private HoleScoreModel GetCurrentHoleScore()
        {
            return mGameHoleScores.get( getAdapterPosition() );
        }

        private void ModifyHoleScore(boolean isIncrease)
        {
            HoleScoreModel model = GetCurrentHoleScore();
            int currentStrokes   = model.getStrokes();

            int newStrokes = isIncrease
                ? currentStrokes + 1
                : currentStrokes - 1;

            model.setStrokes(newStrokes);

            // set score
            int score = newStrokes - model.getHolePar();
            model.setScore(score);

            bttnDecrease.setEnabled(newStrokes > 1);

            notifyDataSetChanged();
        }
    }
}
