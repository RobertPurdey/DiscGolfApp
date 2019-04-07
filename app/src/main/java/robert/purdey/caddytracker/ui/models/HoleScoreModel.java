package robert.purdey.caddytracker.ui.models;

import java.util.UUID;

public class HoleScoreModel
{
    public UUID IdKey;
    public UUID HoleId;
    public UUID PlayerId;
    public UUID RoundId;
    public int Strokes;
    public int Score;
    public int HolePar;

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

    public int getStrokes()
    {
        return Strokes;
    }

    public void setStrokes(int strokes)
    {
        Strokes = strokes;
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

    public int getHolePar()
    {
        return HolePar;
    }

    public void setHolePar(int holePar)
    {
        HolePar = holePar;
    }
}
