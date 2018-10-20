package robert.purdey.caddytracker.ui.models;


import android.arch.lifecycle.MutableLiveData;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class LoginModel
{
    private String username;

    private String password;

    @SerializedName("grant_type")
    private String grantType;

    public LoginModel(String username, String password)
    {
        this.username   = username;
        this.password   = password;
        this.grantType  = "password";
    }

    public Map<String, String> getRequestFields()
    {
        HashMap<String, String> fields = new HashMap<>();

        fields.put("username", username);
        fields.put("password", password);
        fields.put("grant_type", grantType);

        return fields;
    }
}
