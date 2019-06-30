package robert.purdey.caddytracker.domain;

import java.util.UUID;

public class IdModel
{
    public UUID IdKey;

    public IdModel()
    {

    }

    public IdModel(UUID id)
    {
        setIdKey(id);
    }

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }
}
