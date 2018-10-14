package robert.purdey.caddytracker.networking.contracts;

import robert.purdey.caddytracker.networking.arguments.HttpClientArg;

public interface IApiCallManager
{
    IApiCall build(HttpClientArg arg);
}
