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

import java.util.UUID;

import robert.purdey.caddytracker.R;
import robert.purdey.caddytracker.ui.adapters.CourseListAdapter;
import robert.purdey.caddytracker.ui.listeners.IItemClickListener;
import robert.purdey.caddytracker.ui.viewmodels.CourseListViewModel;

public class CourseListFragment extends Fragment implements IItemClickListener
{
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
        final CourseListAdapter courseAdapter      = new CourseListAdapter(activityContext, this);
        LinearLayoutManager layoutManger           = new LinearLayoutManager(activityContext);

        recyclerView.setAdapter(courseAdapter);
        recyclerView.setLayoutManager(layoutManger);

        DividerItemDecoration dividerDecorator = new DividerItemDecoration(
            recyclerView.getContext(),
            layoutManger.getOrientation());

        recyclerView.addItemDecoration(dividerDecorator);

        courseListViewModel = ViewModelProviders.of(this).get(CourseListViewModel.class);

        // todo: this should be able to handle a frolf group id in filter
        courseListViewModel.getCourses().observe(this, courseModels ->
            courseAdapter.setCourses(courseModels)
        );

        return rootView;
    }

    @Override
    public void onClick(View v, UUID id)
    {
        if ( courseClickLisetner != null ) {
            courseClickLisetner.onClick(v, id);
        }
    }

    public void SetCourseClickListener(IItemClickListener listener)
    {
        courseClickLisetner = listener;
    }

}
