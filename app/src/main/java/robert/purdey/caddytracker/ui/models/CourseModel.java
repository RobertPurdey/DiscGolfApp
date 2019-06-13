package robert.purdey.caddytracker.ui.models;

import java.util.List;
import java.util.UUID;

import robert.purdey.caddytracker.ui.models.holes.HoleModel;

public class CourseModel
{
    public UUID IdKey;
    public UUID FrolfGroupId;
    public String Name;
    public int Par;
    public int HoleCount;

    public List<HoleModel> Holes;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public UUID getFrolfGroupId()
    {
        return FrolfGroupId;
    }

    public void setFrolfGroupId(UUID frolfGroupId)
    {
        FrolfGroupId = frolfGroupId;
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

    public List<HoleModel> getHoles()
    {
        return Holes;
    }

    public void setHoles(List<HoleModel> holes)
    {
        Holes = holes;
    }
}
