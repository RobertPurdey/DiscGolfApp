package robert.purdey.caddytracker.ui.models;

public class AppUserCreationModel
{
    public String LoginName;
    public String Handle;
    public String Password;
    public String ConfirmPassword;

    public AppUserCreationModel() { }

    public AppUserCreationModel(
        String loginName,
        String handle,
        String password,
        String confirmPassword)
    {
        LoginName           = loginName;
        Handle              = handle;
        Password            = password;
        ConfirmPassword     = confirmPassword;
    }

    public String getLoginName()
    {
        return LoginName;
    }

    public void setLoginName(String loginName)
    {
        LoginName = loginName;
    }

    public String getHandle()
    {
        return Handle;
    }

    public void setHandle(String handle)
    {
        Handle = handle;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String password)
    {
        Password = password;
    }

    public String getConfirmPassword()
    {
        return ConfirmPassword;
    }

    public void setConfirmPassword(String confirmPassword)
    {
        ConfirmPassword = confirmPassword;
    }
}
