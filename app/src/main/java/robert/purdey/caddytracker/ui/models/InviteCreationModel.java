package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

/**
 * Created by r_pur on 2/10/2019.
 */

public class InviteCreationModel
{
    public UUID IdKey;
    public UUID GroupId;
    public String FriendCode;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public UUID getGroupId()
    {
        return GroupId;
    }

    public void setGroupId(UUID groupId)
    {
        GroupId = groupId;
    }

    public String getFriendCode()
    {
        return FriendCode;
    }

    public void setFriendCode(String friendCode)
    {
        FriendCode = friendCode;
    }
}
