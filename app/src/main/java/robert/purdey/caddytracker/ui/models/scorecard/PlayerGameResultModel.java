package robert.purdey.caddytracker.ui.models.scorecard;

import java.util.Map;

public class PlayerGameResultModel
{
    public String PlayerName;
    public int Strokes;
    public int TotalScore;
    public Map<Integer, Integer> Scores;

    public String getPlayerName()
    {
        return PlayerName;
    }

    public void setPlayerName(String playerName)
    {
        PlayerName = playerName;
    }

    public int getStrokes()
    {
        return Strokes;
    }

    public void setStrokes(int strokes)
    {
        Strokes = strokes;
    }

    public int getTotalScore()
    {
        return TotalScore;
    }

    public void setTotalScore(int totalScore)
    {
        TotalScore = totalScore;
    }

    public Map<Integer, Integer> getScores()
    {
        return Scores;
    }

    public void setScores(Map<Integer, Integer> scores)
    {
        Scores = scores;
    }
}
