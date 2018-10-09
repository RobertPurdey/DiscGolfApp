package robert.purdey.caddytracker.ui.models;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Holds details about a user's friend.
 */

public class FriendModel
{
    public UUID IdKey;

    public String NickName;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        this.IdKey = idKey;
    }

    public String getNickName()
    {
        return NickName;
    }

    public void setNickName(String nickName)
    {
        this.NickName = nickName;
    }
}
