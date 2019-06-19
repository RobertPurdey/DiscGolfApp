package robert.purdey.caddytracker.ui.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.UUID;

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
        RecyclerView recyclerView                 = rootView.findViewById(R.id.courseHolesRecycleView);
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
