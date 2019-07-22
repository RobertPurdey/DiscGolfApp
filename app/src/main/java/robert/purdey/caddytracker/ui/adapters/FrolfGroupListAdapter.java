package robert.purdey.caddytracker.ui.adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.models.FrolfGroupModel;


public class FrolfGroupListAdapter extends RecyclerView.Adapter<FrolfGroupListAdapter.FrolfGroupViewHolder>
{

    private final LayoutInflater mInflater;
    private final IItemClickListener clickListener;
    private List<FrolfGroupModel> mFrolfGroups; // Cached copy of frolfGroups

    public FrolfGroupListAdapter(Context context, IItemClickListener listener)
    {
        mInflater     = LayoutInflater.from(context);
        clickListener = listener;
    }

    @Override
    public FrolfGroupListAdapter.FrolfGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_frolf_group,
            parent,
            false);

        return new FrolfGroupListAdapter.FrolfGroupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FrolfGroupListAdapter.FrolfGroupViewHolder holder, int position)
    {
        if (mFrolfGroups != null)
        {
            FrolfGroupModel current = mFrolfGroups.get(position);

            holder.txtvFrolfGroupId.setText(current.getIdKey().toString());
            holder.txtvFrolfGroupName.setText(current.getName());
            holder.itemView.setOnClickListener(
                view -> clickListener.onClick(view, current.getIdKey())
            );
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvFrolfGroupId.setText("");
            holder.txtvFrolfGroupName.setText("Retrieving group data...");
        }
    }

    public void setFrolfGroups(List<FrolfGroupModel> groups)
    {
        mFrolfGroups = groups;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (mFrolfGroups != null)
            return mFrolfGroups.size();
        else
            return 0;
    }

    class FrolfGroupViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvFrolfGroupId;
        private final TextView txtvFrolfGroupName;

        private FrolfGroupViewHolder(View itemView)
        {
            super(itemView);

            txtvFrolfGroupId   = itemView.findViewById(R.id.txtv_group_id);
            txtvFrolfGroupName = itemView.findViewById(R.id.txtv_group_name);
        }
    }
}
