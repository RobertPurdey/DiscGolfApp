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
import robert.purdey.caddytracker.ui.models.CourseModel;

public class CourseListAdapter extends RecyclerView.Adapter<CourseListAdapter.CourseViewHolder>
{

    private final LayoutInflater mInflater;
    private final IItemClickListener clickListener;
    private List<CourseModel> mCourses; // Cached copy of frolfGroups

    public CourseListAdapter(Context context, IItemClickListener listener)
    {
        mInflater     = LayoutInflater.from(context);
        clickListener = listener;
    }

    @Override
    public CourseListAdapter.CourseViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_course,
            parent,
            false);

        return new CourseListAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseListAdapter.CourseViewHolder holder, int position)
    {
        if (mCourses != null)
        {
            CourseModel current = mCourses.get(position);

            holder.txtvCourseId.setText(current.getIdKey().toString());
            holder.txtvCourseName.setText(current.getName());
            holder.txtvCoursePar.setText( Integer.toString(current.getPar()) );
            holder.txtvHoleCount.setText( Integer.toString(current.getHoleCount()) );
            // todo: imp this

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
            holder.txtvCourseId.setText("");
            holder.txtvCourseName.setText("Retrieving group data...");
        }
    }

    public void setCourses(List<CourseModel> courses)
    {
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        if (mCourses != null)
            return mCourses.size();
        else
            return 0;
    }

    class CourseViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvCourseId;
        private final TextView txtvCourseName;
        private final TextView txtvCoursePar;
        private final TextView txtvHoleCount;

        private CourseViewHolder(View itemView)
        {
            super(itemView);

            txtvCourseId     = itemView.findViewById(R.id.txtv_row_course_id);
            txtvCourseName   = itemView.findViewById(R.id.txtv_row_course_name);
            txtvCoursePar    = itemView.findViewById(R.id.txtv_row_course_par);
            txtvHoleCount    = itemView.findViewById(R.id.txtv_row_hole_count);
        }
    }
}
