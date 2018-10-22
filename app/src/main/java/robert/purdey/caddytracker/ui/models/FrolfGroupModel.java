package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

public class FrolfGroupModel
{
    public UUID IdKey;
    public String Name;

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
}
