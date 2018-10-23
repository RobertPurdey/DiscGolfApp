package robert.purdey.caddytracker.networking.contracts.services;

import robert.purdey.caddytracker.networking.arguments.HttpClientArg;

public interface IApiCallService
{
    <T> T getApiCall(final HttpClientArg arg, final Class <T> service);
}
