package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class FrolfGroupInviteListAdapter extends RecyclerView.Adapter<FrolfGroupInviteListAdapter.FrolfGroupInviteViewHolder>
{

    private final LayoutInflater mInflater;
    private List<FrolfGroupInviteModel> mFrolfGroupInvites; // Cached copy of frolfGroupInvites

    public FrolfGroupInviteListAdapter(Context context)
    {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FrolfGroupInviteListAdapter.FrolfGroupInviteViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_frolf_group_invite,
            parent,
            false);

        return new FrolfGroupInviteListAdapter.FrolfGroupInviteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FrolfGroupInviteListAdapter.FrolfGroupInviteViewHolder holder, int position)
    {
        if (mFrolfGroupInvites != null)
        {
            FrolfGroupInviteModel current = mFrolfGroupInvites.get(position);

            holder.txtvFrolfGroupInviteId.setText(current.getIdKey().toString());
            holder.txtvGroupName.setText(current.getGroupName());
            holder.txtvInviterHandle.setText(current.getInviterHandle());
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvFrolfGroupInviteId.setText("");
            holder.txtvGroupName.setText("Retrieving group name...");
            holder.txtvInviterHandle.setText("Retrieving inviter handle...");
        }
    }

    public void setFrolfGroupInvites(List<FrolfGroupInviteModel> groupInvites)
    {
        mFrolfGroupInvites = groupInvites;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mFriends has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount()
    {
        if (mFrolfGroupInvites != null)
            return mFrolfGroupInvites.size();
        else
            return 0;
    }

    class FrolfGroupInviteViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvFrolfGroupInviteId;
        private final TextView txtvGroupName;
        private final TextView txtvInviterHandle;

        private FrolfGroupInviteViewHolder(View itemView)
        {
            super(itemView);

            txtvFrolfGroupInviteId   = itemView.findViewById(R.id.txtv_frolf_group_invite_id);
            txtvGroupName            = itemView.findViewById(R.id.txtv_invite_group_name);
            txtvInviterHandle        = itemView.findViewById(R.id.txtv_inviter_handle);
        }
    }
}
