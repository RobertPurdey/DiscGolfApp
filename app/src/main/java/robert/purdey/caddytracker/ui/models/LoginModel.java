package robert.purdey.caddytracker.ui.models;


import com.google.gson.annotations.SerializedName;

public class LoginModel
{
    public String username;

    public String password;

    @SerializedName("grant_type")
    private String grantType;

    public LoginModel(String username, String password)
    {
        this.username   = username;
        this.password   = password;
        this.grantType  = "password";
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getGrantType()
    {
        return grantType;
    }
}
