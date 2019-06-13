package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.ui.models.CourseModel;

public interface ICourseController
{
    MutableLiveData<CourseModel> getCourse(UUID id);
    MutableLiveData<List<CourseModel>> getAll();
    MutableLiveData<List<CourseModel>> getWithFilter(CourseFilter filter);
    MutableLiveData<CourseModel> insert(CourseModel newModel);
}
