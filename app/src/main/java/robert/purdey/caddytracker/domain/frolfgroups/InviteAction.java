package robert.purdey.caddytracker.domain.frolfgroups;

public enum InviteAction
{
    Accept(1),
    Decline(2),
    Block(3);

    public final int value;

    InviteAction(int value)
    {
        this.value = value;
    }
}
