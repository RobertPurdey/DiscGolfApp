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
import robert.purdey.caddytracker.ui.models.holes.HoleModel;
import robert.purdey.caddytracker.utilities.Integers;

public class CourseHolesAdapter extends RecyclerView.Adapter<CourseHolesAdapter.CourseHoleViewHolder>
{

    private final LayoutInflater mInflater;
    // Cached copy of GameHoleScoress
    private List<HoleModel> mCourseHoles;

    public CourseHolesAdapter(Context context)
    {
        mInflater  = LayoutInflater.from(context);
    }

    @Override
    public CourseHolesAdapter.CourseHoleViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = mInflater.inflate(
            R.layout.row_item_course_hole,
            parent,
            false);

        return new CourseHolesAdapter.CourseHoleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CourseHolesAdapter.CourseHoleViewHolder holder, int position)
    {
        if (mCourseHoles != null)
        {
            HoleModel current = mCourseHoles.get(position);

            holder.txtvHoleId.setText(current.getIdKey().toString());
            holder.txtvCourseId.setText(current.getCourseId().toString());
            holder.txtvTee.setText(Integer.toString(current.getOrder()));
            holder.txtvPar.setText(Integer.toString(current.getPar()));
            holder.bttnDecrease.setEnabled(current.getPar() > 1 );
        }
        else
        {
            // Covers the case of data not being ready yet.
            holder.txtvHoleId.setText("");
            holder.txtvCourseId.setText("");
            holder.txtvTee.setText("0");
            holder.txtvPar.setText("0");
        }
    }

    public void setCourseHoles(List<HoleModel> holes)
    {
        mCourseHoles = holes;
        notifyDataSetChanged();
    }

    public void addCourseHole(HoleModel hole)
    {
        mCourseHoles.add(hole);
        notifyDataSetChanged();
    }

    public void removeCourseHole()
    {
        if (mCourseHoles.size() != 0)
        {
            mCourseHoles.remove(mCourseHoles.size()-1);
            notifyDataSetChanged();
        }
    }

    public List<HoleModel> getCourseHoles()
    {
        return mCourseHoles;
    }

    // getItemCount() is called many times, and when it is first called,
    // mFriends has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount()
    {
        return mCourseHoles != null
            ? mCourseHoles.size()
            : 0;
    }

    class CourseHoleViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtvHoleId;
        private final TextView txtvCourseId;
        private final TextView txtvTee;
        private final TextView txtvPar;
        private final Button bttnIncrease;
        private final Button bttnDecrease;

        private CourseHoleViewHolder(View itemView)
        {
            super(itemView);

            txtvHoleId          = itemView.findViewById(R.id.txtv_row_item_course_hole_id);
            txtvCourseId        = itemView.findViewById(R.id.txtv_row_item_course_hole_course_id);
            txtvTee             = itemView.findViewById(R.id.txtv_row_item_course_hole_tee);
            txtvPar             = itemView.findViewById(R.id.txtv_row_item_course_hole_par);
            bttnIncrease        = itemView.findViewById(R.id.bttn_row_item_course_hole_par_increase);
            bttnDecrease        = itemView.findViewById(R.id.bttn_row_item_course_hole_par_decrease);

            setIncreaseOnClick();
            setDecreaseOnClick();
        }

        private void setIncreaseOnClick()
        {
            bttnIncrease.setOnClickListener(view -> ModifyHoleScore(true) );
        }

        private void setDecreaseOnClick()
        {
            bttnDecrease.setOnClickListener(view -> ModifyHoleScore(false) );
        }

        private HoleModel GetCurrentHole()
        {
            return mCourseHoles.get( getAdapterPosition() );
        }

        private void ModifyHoleScore(boolean isIncrease)
        {
            HoleModel model      = GetCurrentHole();
            int currentPar       = model.getPar();

            int newPar = isIncrease
                ? currentPar + 1
                : currentPar - 1;

            model.setPar(newPar);

            bttnDecrease.setEnabled(newPar > 1);

            notifyDataSetChanged();
        }
    }
}
