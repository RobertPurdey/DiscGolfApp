package robert.purdey.caddytracker.ui.models;


import java.util.UUID;

public class PlayerModel
{
    public UUID IdKey;
    public UUID AppUserId;
    public String Handle;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public String getHandle()
    {
        return Handle;
    }

    public void setHandle(String handle)
    {
        Handle = handle;
    }

    public UUID getAppUserId()
    {
        return AppUserId;
    }

    public void setAppUserId(UUID appUserId)
    {
        AppUserId = appUserId;
    }
}
