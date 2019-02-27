package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

public class CourseModel
{
    public UUID IdKey;
    public String Name;
    public int Par;
    public int HoleCount;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public int getPar()
    {
        return Par;
    }

    public void setPar(int par)
    {
        Par = par;
    }

    public int getHoleCount()
    {
        return HoleCount;
    }

    public void setHoleCount(int holeCount)
    {
        HoleCount = holeCount;
    }
}
