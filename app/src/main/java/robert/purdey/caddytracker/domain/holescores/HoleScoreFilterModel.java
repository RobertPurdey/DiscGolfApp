package robert.purdey.caddytracker.domain.holescores;

import java.util.UUID;

public class HoleScoreFilterModel
{
    public UUID GameID;
    public Integer HoleNumber;

    public UUID getGameID()
    {
        return GameID;
    }

    public void setGameID(UUID gameID)
    {
        GameID = gameID;
    }

    public Integer getHoleNumber()
    {
        return HoleNumber;
    }

    public void setHoleNumber(Integer holeNumber)
    {
        HoleNumber = holeNumber;
    }
}
