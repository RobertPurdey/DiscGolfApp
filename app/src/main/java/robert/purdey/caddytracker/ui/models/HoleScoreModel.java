package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

public class HoleScoreModel
{
    public UUID IdKey;
    public UUID HoleId;
    public UUID PlayerId;
    public UUID RoundId;
    public int Score;
    public String PlayerHandle;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public UUID getHoleId()
    {
        return HoleId;
    }

    public void setHoleId(UUID holeId)
    {
        HoleId = holeId;
    }

    public UUID getPlayerId()
    {
        return PlayerId;
    }

    public void setPlayerId(UUID playerId)
    {
        PlayerId = playerId;
    }

    public UUID getRoundId()
    {
        return RoundId;
    }

    public void setRoundId(UUID roundId)
    {
        RoundId = roundId;
    }

    public int getScore()
    {
        return Score;
    }

    public void setScore(int score)
    {
        Score = score;
    }

    public String getPlayerHandle()
    {
        return PlayerHandle;
    }

    public void setPlayerHandle(String playerHandle)
    {
        PlayerHandle = playerHandle;
    }
}
