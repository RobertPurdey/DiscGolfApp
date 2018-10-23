package robert.purdey.caddytracker.networking.contracts.calls;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiCall
{
    //todo: use configuration file?
    String BASE_URL = "http://192.168.1.65:53739/";
}
