package robert.purdey.caddytracker.domain.players;

import java.util.UUID;

public class RemovePlayerModel
{
    public UUID FrolfGroupId;
    public UUID PlayerId;

    public RemovePlayerModel()
    {

    }

    public RemovePlayerModel(UUID frolfGroupId, UUID playerId)
    {
        setFrolfGroupId(frolfGroupId);
        setPlayerId(playerId);
    }

    public UUID getFrolfGroupId()
    {
        return FrolfGroupId;
    }

    public void setFrolfGroupId(UUID frolfGroupId)
    {
        FrolfGroupId = frolfGroupId;
    }

    public UUID getPlayerId()
    {
        return PlayerId;
    }

    public void setPlayerId(UUID playerId)
    {
        PlayerId = playerId;
    }
}
