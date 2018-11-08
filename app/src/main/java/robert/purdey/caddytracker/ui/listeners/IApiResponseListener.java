package robert.purdey.caddytracker.ui.listeners;

public interface IApiResponseListener
{
    void onResponseSuccessful();
    void onResponseFailed();
    void onCallFailure();
}
