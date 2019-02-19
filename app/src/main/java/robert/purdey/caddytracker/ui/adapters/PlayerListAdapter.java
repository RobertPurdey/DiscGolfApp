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
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;
import robert.purdey.caddytracker.ui.models.PlayerModel;

/**
 * Created by r_pur on 2/18/2019.
 */

public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>
{

    private final LayoutInflater mInflater;
    //private final IItemClickListener clickListener;
    private List<PlayerModel> mPlayers; // Cached copy of frolfGroups

    public PlayerListAdapter(Context context)// todo: imp this =>, IItemClickListener listener)
    {
        mInflater     = LayoutInflater.from(context);
        //clickListener = listener;
    }

    @Override
    public PlayerListAdapter.PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_frolf_group_member,
            parent,
            false);

        return new PlayerListAdapter.PlayerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PlayerListAdapter.PlayerViewHolder holder, int position)
    {
        if (mPlayers != null)
        {
            PlayerModel current = mPlayers.get(position);

            holder.txtvPlayerId.setText(current.getIdKey().toString());
            holder.txtvPlayerHandle.setText(current.getHandle());
            //todo: click listener: holder.itemView.setOnClickListener(
            //    view -> clickListener.onClick(view, current.getIdKey())
            //);
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvPlayerId.setText("");
            holder.txtvPlayerHandle.setText("Retrieving group data...");
        }
    }

    public void setPlayers(List<PlayerModel> players)
    {
        mPlayers = players;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (mPlayers != null)
            return mPlayers.size();
        else
            return 0;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvPlayerId;
        private final TextView txtvPlayerHandle;

        private PlayerViewHolder(View itemView)
        {
            super(itemView);

            txtvPlayerId     = itemView.findViewById(R.id.txtv_player_id);
            txtvPlayerHandle = itemView.findViewById(R.id.txtv_handle);
        }
    }
}
