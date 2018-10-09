package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

/**
 * Holds details about a user's friend.
 */

public class FriendModel
{
    public UUID idKey;
    public String nickName;

    public UUID getIdKey()
    {
        return idKey;
    }

    public void setIdKey(UUID idKey)
    {
        this.idKey = idKey;
    }

    public String getNickName()
    {
        return nickName;
    }

    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }
}
