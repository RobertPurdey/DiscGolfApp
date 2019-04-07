package robert.purdey.caddytracker.ui.models;

import java.util.List;
import java.util.Map;

import robert.purdey.caddytracker.ui.models.scorecard.PlayerGameResultModel;

public class GameResultModel
{
    public String CourseName;
    public int CoursePar;
    public int HoleCount;
    public Map<Integer, Integer> HolePars;
    public List<PlayerGameResultModel> PlayerResults;

    public String getCourseName()
    {
        return CourseName;
    }

    public void setCourseName(String courseName)
    {
        CourseName = courseName;
    }

    public int getCoursePar()
    {
        return CoursePar;
    }

    public void setCoursePar(int coursePar)
    {
        CoursePar = coursePar;
    }

    public int getHoleCount()
    {
        return HoleCount;
    }

    public void setHoleCount(int holeCount)
    {
        HoleCount = holeCount;
    }

    public Map<Integer, Integer> getHolePars()
    {
        return HolePars;
    }

    public void setHolePars(Map<Integer, Integer> holePars)
    {
        HolePars = holePars;
    }

    public List<PlayerGameResultModel> getPlayerResults()
    {
        return PlayerResults;
    }

    public void setPlayerResults(List<PlayerGameResultModel> playerResults)
    {
        PlayerResults = playerResults;
    }
}
