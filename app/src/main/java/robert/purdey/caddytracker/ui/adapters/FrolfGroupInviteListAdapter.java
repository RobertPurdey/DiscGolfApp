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
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class FrolfGroupInviteListAdapter extends RecyclerView.Adapter<FrolfGroupInviteListAdapter.FrolfGroupInviteViewHolder>
{

    private final LayoutInflater mInflater;
    // Cached copy of frolfGroupInvites
    private List<FrolfGroupInviteModel> mFrolfGroupInvites;
    private IItemClickListener clickListener;

    public FrolfGroupInviteListAdapter(Context context, IItemClickListener clickListener)
    {
        mInflater          = LayoutInflater.from(context);
        this.clickListener = clickListener;
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
        return mFrolfGroupInvites != null
            ? mFrolfGroupInvites.size()
            : 0;
    }

    class FrolfGroupInviteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
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

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            if (clickListener != null)
            {
                FrolfGroupInviteModel current = mFrolfGroupInvites.get(getAdapterPosition());

                clickListener.onClick(view, current.getIdKey());
            }
        }
    }
}