package robert.purdey.caddytracker.ui.listeners;

import java.util.UUID;

public interface IInviteActionClickListener
{
    void onClickAccept(UUID id);
    void onClickDecline(UUID id);
    // todo: use once block is implemented
    //void onClickBlock(UUID id);
}
