package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.models.HoleScoreModel;


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
            holder.txtvScore.setText(Integer.toString(current.getScore()));
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvHoleScoreId.setText("");
            holder.txtvPlayerName.setText("");
            holder.txtvScore.setText("0");
        }
    }

    public void setGameHoleScores(List<HoleScoreModel> holeScores)
    {
        mGameHoleScores = holeScores;
        notifyDataSetChanged();
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
        private final Button bttnIncrease;
        private final Button bttnDecrease;

        private GameHoleScoresViewHolder(View itemView)
        {
            super(itemView);

            txtvHoleScoreId          = itemView.findViewById(R.id.txtv_row_game_hole_score_hole_score_id);
            txtvPlayerName           = itemView.findViewById(R.id.txtv_row_game_hole_score_player_name);
            txtvScore                = itemView.findViewById(R.id.txtv_row_game_hole_score_score);
            bttnIncrease             = itemView.findViewById(R.id.bttn_row_game_hole_score_increase);
            bttnDecrease             = itemView.findViewById(R.id.bttn_row_game_hole_score_decrease);

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
            int currentScore     = model.getScore();

            int newScore = isIncrease
                ? currentScore + 1
                : currentScore - 1;

            model.setScore(newScore);

            bttnDecrease.setEnabled(newScore > 1);

            notifyDataSetChanged();
        }
    }
}
