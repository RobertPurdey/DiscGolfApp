package robert.purdey.caddytracker.networking.contracts.controllers;

import android.arch.lifecycle.MutableLiveData;
import java.util.List;

import robert.purdey.caddytracker.ui.models.CourseModel;

public interface ICourseController
{
    MutableLiveData<List<CourseModel>> getAll();
}
