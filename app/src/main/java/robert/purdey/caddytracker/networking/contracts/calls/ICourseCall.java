package robert.purdey.caddytracker.networking.contracts.calls;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;

public interface ICourseCall
{
    @POST("api/courses/getById")
    Call<EncryptModel> getById(
        @Header("Authorization") String auth,
        @Body EncryptModel id);

    @GET("api/courses/")
    Call<EncryptModel> getAll(
        @Header("Authorization") String auth);

    @POST("api/courses/filter")
    Call<EncryptModel> getWithFilter(
        @Header("Authorization") String auth,
        @Body EncryptModel courseFilter);

    @POST("api/courses/insert/")
    Call<EncryptModel> insert(
        @Header("Authorization") String auth,
        @Body EncryptModel newCourse);

    @POST("api/courses/update/")
    Call<Void> update(
        @Header("Authorization") String auth,
        @Body EncryptModel course);
}
