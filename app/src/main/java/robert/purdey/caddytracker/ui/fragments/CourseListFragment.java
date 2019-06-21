package robert.purdey.caddytracker.ui.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.ui.activities.CourseRecordActivity;
import robert.purdey.caddytracker.ui.adapters.CourseHolesAdapter;
import robert.purdey.caddytracker.ui.adapters.CourseListAdapter;
import robert.purdey.caddytracker.ui.adapters.GameHoleScoresAdapter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.viewmodels.CourseListViewModel;

public class CourseListFragment extends Fragment implements IItemClickListener
{
    public static final String FROLF_GROUP_ID = "RECORD_ID";

    private CourseListAdapter courseListAdapter;
    private CourseListViewModel courseListViewModel;
    private IItemClickListener courseClickLisetner;

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_course_list, container, false);

        Context activityContext                    = getActivity();
        RecyclerView recyclerView                  = rootView.findViewById(R.id.courseListRecycleView);
        courseListAdapter                          = new CourseListAdapter(activityContext, this);
        LinearLayoutManager layoutManger           = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(courseListAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        courseListViewModel = ViewModelProviders.of(this).get(CourseListViewModel.class);

        return rootView;
    }

    public void LoadCourses(CourseFilter filter)
    {
        courseListViewModel.getCourses(filter).observe(this, courseModels ->
            courseListAdapter.setCourses(courseModels)
        );
    }

    @Override
    public void onClick(View v, UUID id)
    {
        if ( courseClickLisetner != null ) {
            courseClickLisetner.onClick(v, id);
        }
    }

    public void setCourseClickListener(IItemClickListener listener)
    {
        courseClickLisetner = listener;
    }

}
