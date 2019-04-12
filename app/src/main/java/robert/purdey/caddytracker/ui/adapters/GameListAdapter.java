package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.models.GameModel;

public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder>
{

    private final LayoutInflater mInflater;
    private final IItemClickListener clickListener;
    private List<GameModel> mGames; // Cached copy of games

    public GameListAdapter(Context context, IItemClickListener listener)
    {
        mInflater     = LayoutInflater.from(context);
        clickListener = listener;
    }

    @Override
    public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_game,
            parent,
            false);

        return new GameListAdapter.GameViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(GameListAdapter.GameViewHolder holder, int position)
    {
        if (mGames != null)
        {
            GameModel current = mGames.get(position);

            holder.txtvGameId.setText(current.getIdKey().toString());
            holder.txtvGameName.setText(current.getName());
            holder.txtvCourseName.setText(current.getCourseName());

            if (clickListener != null)
            {
                holder.itemView.setOnClickListener(
                    view -> clickListener.onClick(view, current.getIdKey())
                );
            }
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvGameId.setText("");
            holder.txtvGameName.setText("Retrieving group data...");
            holder.txtvCourseName.setText("Retrieving group data...");
        }
    }

    public void setGames(List<GameModel> Games)
    {
        mGames = Games;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (mGames != null)
            return mGames.size();
        else
            return 0;
    }

    class GameViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvGameId;
        private final TextView txtvGameName;
        private final TextView txtvCourseName;

        private GameViewHolder(View itemView)
        {
            super(itemView);

            txtvGameId              = itemView.findViewById(R.id.txtv_row_game_id);
            txtvGameName            = itemView.findViewById(R.id.txtv_row_game_name);
            txtvCourseName          = itemView.findViewById(R.id.txtv_row_game_course_name);
        }
    }
}