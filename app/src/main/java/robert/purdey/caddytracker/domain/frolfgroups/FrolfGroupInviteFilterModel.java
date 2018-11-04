package robert.purdey.caddytracker.domain.frolfgroups;

import org.jetbrains.annotations.Nullable;

public class FrolfGroupInviteFilterModel
{
    @Nullable
    public InviteState InviteStatus;

    public FrolfGroupInviteFilterModel(@Nullable InviteState status)
    {
        InviteStatus = status;
    }
}
