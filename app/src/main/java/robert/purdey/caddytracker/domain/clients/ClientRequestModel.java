package robert.purdey.caddytracker.domain.clients;

import java.util.UUID;

public class ClientRequestModel
{
    public String token;
    public String command;
    public UUID gameId;

    public ClientRequestModel(String token, String command, UUID gameId)
    {
        this.token    = token;
        this.command  = command;
        this.gameId   = gameId;
    }
}
