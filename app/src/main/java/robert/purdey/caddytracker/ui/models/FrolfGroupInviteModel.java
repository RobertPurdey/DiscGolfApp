package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

public class FrolfGroupInviteModel
{
    public UUID IdKey;

    public String InviterName;

    public String GroupName;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public String getInviterName()
    {
        return InviterName;
    }

    public void setInviterName(String inviterName)
    {
        InviterName = inviterName;
    }

    public String getGroupName()
    {
        return GroupName;
    }

    public void setGroupName(String groupName)
    {
        GroupName = groupName;
    }
}
