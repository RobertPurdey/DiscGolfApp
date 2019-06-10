package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

public class AppUserUpdateModel
{
    public UUID IdKey;
    public String LoginName;
    public String Handle;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
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
}
