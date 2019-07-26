package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.models.PlayerModel;


public class PlayerListAdapter extends RecyclerView.Adapter<PlayerListAdapter.PlayerViewHolder>
{

    private final LayoutInflater mInflater;
    private final IItemClickListener clickListener;
    private List<PlayerModel> mPlayers;
    private HashSet<UUID> selectedIds;
    private boolean showSelection;

    public PlayerListAdapter(Context context, IItemClickListener listener)
    {
        mInflater           = LayoutInflater.from(context);
        clickListener       = listener;
        selectedIds         = new HashSet<>();
        showSelection       = false;
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
            PlayerModel current     = mPlayers.get(position);
            UUID key                = current.getIdKey();

            holder.txtvPlayerId.setText(current.getIdKey().toString());
            holder.txtvPlayerHandle.setText(current.getHandle());

            if (clickListener != null)
            {

                holder.itemView.setOnClickListener(view -> {
                    if ( selectedIds.contains(key) )
                    {
                        if (showSelection)
                        {
                            selectedIds.remove(key);
                            holder.playerLayout.setSelected(false);
                        }
                    }
                    else
                    {
                        if (showSelection)
                        {
                            selectedIds.add(key);
                            holder.playerLayout.setSelected(true);
                        }
                    }

                    clickListener.onClick(view, key);
                });
            }
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
        mPlayers     = players;
        selectedIds  = new HashSet<>();

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

    public void setShowSelection(boolean isShow)
    {
        showSelection = isShow;
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvPlayerId;
        private final TextView txtvPlayerHandle;
        private final RelativeLayout playerLayout;

        private boolean isSelected;

        private PlayerViewHolder(View itemView)
        {
            super(itemView);

            playerLayout     = itemView.findViewById(R.id.row_item_frolf_group_member);
            txtvPlayerId     = itemView.findViewById(R.id.txtv_row_item_frolf_group_member_player_id);
            txtvPlayerHandle = itemView.findViewById(R.id.txtv_row_item_frolf_group_member_handle);
        }
    }
}
