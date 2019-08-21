package robert.purdey.caddytracker.ui.models.scorecard;

import java.util.Map;

public class PlayerGameResultModel
{
    public String PlayerName;
    public int TotalStrokes;
    public int TotalScore;
    public int Rank;
    public Map<Integer, Integer> Strokes;
    public Map<Integer, Integer> Scores;

    public String getPlayerName()
    {
        return PlayerName;
    }

    public void setPlayerName(String playerName)
    {
        PlayerName = playerName;
    }

    public int getTotalStrokes()
    {
        return TotalStrokes;
    }

    public void setTotalStrokes(int totalStrokes)
    {
        TotalStrokes = totalStrokes;
    }

    public int getTotalScore()
    {
        return TotalScore;
    }

    public void setTotalScore(int totalScore)
    {
        TotalScore = totalScore;
    }

    public int getRank()
    {
        return Rank;
    }

    public void setRank(int rank)
    {
        Rank = rank;
    }

    public Map<Integer, Integer> getStrokes()
    {
        return Strokes;
    }

    public void setStrokes(Map<Integer, Integer> strokes)
    {
        Strokes = strokes;
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
