package robert.purdey.caddytracker.domain.courses;


import androidx.annotation.Nullable;

import java.util.UUID;

public class CourseFilter
{
    @Nullable
    public UUID FrolfGroupId;

    @Nullable
    public UUID getFrolfGroupId()
    {
        return FrolfGroupId;
    }

    public void setFrolfGroupId(@Nullable UUID frolfGroupId)
    {
        FrolfGroupId = frolfGroupId;
    }
}
