package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

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
            GameModel current   = mGames.get(position);
            String date         = holder.dateFormatter
                .format(current.getCreatedDate())
                .toString();

            holder.txtvGameId.setText(current.getIdKey().toString());
            holder.txtvGameName.setText(current.getName());
            holder.txtvCourseName.setText(current.getCourseName());
            holder.txtvGroupName.setText(current.getGroupName());
            holder.txtvCreatedDate.setText(date);

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
            holder.txtvCreatedDate.setText("");
            holder.txtvGameName.setText("Retrieving game data...");
            holder.txtvCourseName.setText("Retrieving game data...");
            holder.txtvGroupName.setText("Retrieving game data...");
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
        private final TextView txtvCreatedDate;
        private final TextView txtvCourseName;
        private final TextView txtvGroupName;

        private final SimpleDateFormat dateFormatter;

        private GameViewHolder(View itemView)
        {
            super(itemView);

            txtvGameId              = itemView.findViewById(R.id.txtv_row_item_game_id);
            txtvGameName            = itemView.findViewById(R.id.txtv_row_item_game_name);
            txtvCreatedDate         = itemView.findViewById(R.id.txtv_row_item_game_created_date);
            txtvCourseName          = itemView.findViewById(R.id.txtv_row_item_game_course_name);
            txtvGroupName           = itemView.findViewById(R.id.txtv_row_item_game_group_name);

            dateFormatter = new SimpleDateFormat("EEE MMM dd yyyy HH:mm a");
            // todo: how to make local date (default timezone was not working.. could be an emulator phone setting)
            dateFormatter.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        }
    }
}