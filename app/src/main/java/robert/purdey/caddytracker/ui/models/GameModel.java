package robert.purdey.caddytracker.ui.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameModel
{
    public UUID IdKey;
    public UUID CreatorId;
    public String Name;
    public List HoleIds;

    public GameModel()
    {
        HoleIds = new ArrayList<Integer>();
    }

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public UUID getCreatorId()
    {
        return CreatorId;
    }

    public void setCreatorId(UUID creatorId)
    {
        CreatorId = creatorId;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public List getHoleIds()
    {
        return HoleIds;
    }

    public void setHoleIds(List holeIds)
    {
        HoleIds = holeIds;
    }
}
