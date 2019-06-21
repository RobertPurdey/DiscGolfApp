package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import robert.purdey.caddytracker.domain.courses.CourseFilter;
import robert.purdey.caddytracker.ui.models.CourseModel;

public interface ICourseCall
{
    @GET("api/courses/{id}/")
    Call<CourseModel> getById(
        @Header("Authorization") String auth,
        @Path("id") UUID id);

    @GET("api/courses/")
    Call<List<CourseModel>> getAll(
        @Header("Authorization") String auth);

    @POST("api/courses/filter")
    Call<List<CourseModel>> getWithFilter(
        @Header("Authorization") String auth,
        @Body CourseFilter courseFilter);

    @POST("api/courses/insert/")
    Call<CourseModel> insert(
        @Header("Authorization") String auth,
        @Body CourseModel newCourse);

    @POST("api/courses/update/")
    Call<Void> update(
        @Header("Authorization") String auth,
        @Body CourseModel course);
}
