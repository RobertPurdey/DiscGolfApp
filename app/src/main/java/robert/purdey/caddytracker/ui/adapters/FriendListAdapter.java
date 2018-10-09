package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.List;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.models.FriendModel;

/**
 *
 */
public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendViewHolder>
{

    private final LayoutInflater mInflater;
    private List<FriendModel> mFriends; // Cached copy of friends

    public FriendListAdapter(Context context)
    {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_friend,
            parent,
            false);

        return new FriendViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FriendViewHolder holder, int position)
    {
        if (mFriends != null)
        {
            FriendModel current = mFriends.get(position);

            holder.txtvFriendName.setText(current.getNickName());
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvFriendName.setText("Retrieving friend data...");
        }
    }

    public void setFriends(List<FriendModel> friends)
    {
        mFriends = friends;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mFriends has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount()
    {
        if (mFriends != null)
            return mFriends.size();
        else
            return 0;
    }

    // todo: move this to its own public class ?
    class FriendViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvFriendName;

        private FriendViewHolder(View itemView)
        {
            super(itemView);
            txtvFriendName = itemView.findViewById(R.id.txtv_friend_name);
        }
    }
}
