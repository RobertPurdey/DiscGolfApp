package robert.purdey.caddytracker.domain.frolfgroups;

public enum InviteState
{
    Pending(1),
    Accepted(2),
    Declined(3);

    public final int value;

    InviteState(int value)
    {
        this.value = value;
    }
}
