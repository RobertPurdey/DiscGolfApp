package robert.purdey.caddytracker.ui.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import robert.purdey.caddytracker.domain.games.GameState;

public class GameModel
{
    public UUID IdKey;
    public UUID CreatorId;

    public Date CreatedDate;

    public String Name;
    public String CourseName;
    public String GroupName;

    public int CoursePar;
    public int CourseHoleCount;

    public GameState State;

    public List HoleIds;
    public Map<Integer, Integer> HolePars;

    public GameModel()
    {
        HoleIds = new ArrayList<Integer>();
    }

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public UUID getCreatorId()
    {
        return CreatorId;
    }

    public void setCreatorId(UUID creatorId)
    {
        CreatorId = creatorId;
    }

    public String getName()
    {
        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public String getCourseName()
    {
        return CourseName;
    }

    public String getGroupName()
    {
        return GroupName;
    }

    public void setGroupName(String groupName)
    {
        GroupName = groupName;
    }

    public GameState getState()
    {
        return State;
    }

    public void setState(GameState state)
    {
        State = state;
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

    public int getCourseHoleCount()
    {
        return CourseHoleCount;
    }

    public void setCourseHoleCount(int courseHoleCount)
    {
        CourseHoleCount = courseHoleCount;
    }

    public List getHoleIds()
    {
        return HoleIds;
    }

    public void setHoleIds(List holeIds)
    {
        HoleIds = holeIds;
    }

    public Map<Integer, Integer> getHolePars()
    {
        return HolePars;
    }

    public void setHolePars(Map<Integer, Integer> holePars)
    {
        HolePars = holePars;
    }

    public Date getCreatedDate()
    {
        return CreatedDate;
    }

    public void setCreatedDate(Date createdDate)
    {
        CreatedDate = createdDate;
    }
}
