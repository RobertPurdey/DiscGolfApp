package robert.purdey.caddytracker.ui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameCreationModel
{
    public UUID IdKey;
    public UUID GroupId;
    public UUID CourseId;
    public List<UUID> PlayerIds;
    public String Name;

    public GameCreationModel()
    {
        PlayerIds = new ArrayList<>();
    }

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

    public UUID getCourseId()
    {
        return CourseId;
    }

    public void setCourseId(UUID courseId)
    {
        CourseId = courseId;
    }

    public List<UUID> getPlayerIds()
    {
        return PlayerIds;
    }

    public void setPlayerIds(List<UUID> playerIds)
    {
        PlayerIds = playerIds;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }
}
