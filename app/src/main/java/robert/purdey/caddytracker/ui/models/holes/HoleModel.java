package robert.purdey.caddytracker.ui.models.holes;

import java.util.UUID;

public class HoleModel
{
    public UUID IdKey;

    public UUID CourseId;

    public int Par;

    public int Order;

    public UUID getIdKey()
    {
        return IdKey;
    }

    public void setIdKey(UUID idKey)
    {
        IdKey = idKey;
    }

    public UUID getCourseId()
    {
        return CourseId;
    }

    public void setCourseId(UUID courseId)
    {
        CourseId = courseId;
    }

    public int getPar()
    {
        return Par;
    }

    public void setPar(int par)
    {
        Par = par;
    }

    public int getOrder()
    {
        return Order;
    }

    public void setOrder(int order)
    {
        Order = order;
    }
}
