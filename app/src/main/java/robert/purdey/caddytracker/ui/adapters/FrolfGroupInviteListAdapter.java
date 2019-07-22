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
import robert.purdey.caddytracker.ui.listeners.IInviteActionClickListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupInviteModel;

public class FrolfGroupInviteListAdapter extends RecyclerView.Adapter<FrolfGroupInviteListAdapter.FrolfGroupInviteViewHolder>
{

    private final LayoutInflater mInflater;
    // Cached copy of frolfGroupInvites
    private List<FrolfGroupInviteModel> mFrolfGroupInvites;
    private IInviteActionClickListener clickListener;

    public FrolfGroupInviteListAdapter(Context context, IInviteActionClickListener clickListener)
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

    class FrolfGroupInviteViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvFrolfGroupInviteId;
        private final TextView txtvGroupName;
        private final TextView txtvInviterHandle;
        private final Button bttnAccept;
        private final Button bttnDecline;

        // todo: use this once block is implemented
        //private final Button bttnBlock;

        private FrolfGroupInviteViewHolder(View itemView)
        {
            super(itemView);

            txtvFrolfGroupInviteId   = itemView.findViewById(R.id.txtv_frolf_group_invite_id);
            txtvGroupName            = itemView.findViewById(R.id.txtv_invite_group_name);
            txtvInviterHandle        = itemView.findViewById(R.id.txtv_inviter_handle);
            bttnAccept               = itemView.findViewById(R.id.bttn_accept_invite);
            bttnDecline              = itemView.findViewById(R.id.bttn_decline_invite);

            // Only set buttons if a listener was provided
            if ( clickListener != null )
            {
                setAcceptOnClick();
                setDeclineOnClick();
            }

        }

        private void setAcceptOnClick()
        {
            bttnAccept.setOnClickListener(view -> clickListener.onClickAccept(
                GetCurrentInvite().getIdKey() ));
        }

        private void setDeclineOnClick()
        {
            bttnDecline.setOnClickListener(view -> clickListener.onClickDecline(
                GetCurrentInvite().getIdKey() ));
        }

        private FrolfGroupInviteModel GetCurrentInvite()
        {
            return mFrolfGroupInvites.get( getAdapterPosition() );
        }


        // todo: block invite - implement when ready
        //private void setBlockOnClick()
        //{
        //
        //}
    }
}