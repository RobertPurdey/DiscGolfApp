package robert.purdey.caddytracker.ui.models;

import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class LoginModel
{
    public String username;

    public String password;

    @SerializedName("grant_type")
    public String grantType;

    public LoginModel(String username, String password)
    {
        this.username   = username;
        this.password   = password;
        this.grantType  = "password";
    }
}
