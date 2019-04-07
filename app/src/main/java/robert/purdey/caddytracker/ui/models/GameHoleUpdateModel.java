package robert.purdey.caddytracker.ui.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GameHoleUpdateModel
{
    public UUID gameId;
    public Map<UUID, Integer> HoleScoreUpdates;

    public GameHoleUpdateModel()
    {
        HoleScoreUpdates = new HashMap<>();
    }

    public UUID getGameId()
    {
        return gameId;
    }

    public void setGameId(UUID gameId)
    {
        this.gameId = gameId;
    }

    public Map<UUID, Integer> getHoleScoreUpdates()
    {
        return HoleScoreUpdates;
    }

    public void setHoleScoreUpdates(List<HoleScoreModel> holeScoreUpdates)
    {
        HoleScoreUpdates.clear();

        for ( HoleScoreModel hole : holeScoreUpdates )
        {
            HoleScoreUpdates.put(hole.getIdKey(), hole.getStrokes());
        }
    }
}
