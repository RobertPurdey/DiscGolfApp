package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import robert.purdey.caddytracker.ui.models.CourseModel;

public interface ICourseCall
{
    @GET("api/frolfgroupinvites/")
    Call<List<CourseModel>> getAll(
        @Header("Authorization") String auth);
}
