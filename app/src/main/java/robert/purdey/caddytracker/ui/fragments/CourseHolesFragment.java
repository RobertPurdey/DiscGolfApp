package robert.purdey.caddytracker.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.CourseHolesAdapter;
import robert.purdey.caddytracker.ui.models.holes.HoleModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseHolesFragment extends Fragment
{
    private CourseHolesAdapter courseHolesAdapter;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_course_holes, container, false);

        Context activityContext                   = getActivity();
        RecyclerView recyclerView                 = rootView.findViewById(R.id.rcvw_fragment_course_course_holes);
        courseHolesAdapter                        = new CourseHolesAdapter(activityContext);
        LinearLayoutManager layoutManger          = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(courseHolesAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        return rootView;
    }

    public void Load(List<HoleModel> courseHoles)
    {
        courseHolesAdapter.setCourseHoles(courseHoles);
    }

    public void addHole(HoleModel hole)
    {
        courseHolesAdapter.addCourseHole(hole);
    }

    public void removeHole()
    {
        courseHolesAdapter.removeCourseHole();
    }

    public List<HoleModel> getCourseHoles()
    {
        return courseHolesAdapter.getCourseHoles();
    }
}
