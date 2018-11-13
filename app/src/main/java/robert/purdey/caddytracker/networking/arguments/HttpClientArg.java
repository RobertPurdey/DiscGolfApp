package robert.purdey.caddytracker.networking.arguments;

import java.util.Map;
import okhttp3.Authenticator;

/**
 * Information required to build an Http Client.
 */
public class HttpClientArg
{
    private int readTimeout;
    private int connectionTimeout;
    private String baseUrl;
    private Authenticator apiAuthenticator;

    public HttpClientArg(
        int readTimeout,
        int connectionTimeout,
        String baseUrl,
        Authenticator apiAuthenticator)
    {
        this.readTimeout         = readTimeout;
        this.connectionTimeout   = connectionTimeout;
        this.baseUrl             = baseUrl;
        this.apiAuthenticator    = apiAuthenticator;
    }

    public int getReadTimeout()
    {
        return readTimeout;
    }

    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }

    public String getBaseUrl()
    {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl)
    {
        this.baseUrl = baseUrl;
    }

    public Authenticator getApiAuthenticator()
    {
        return apiAuthenticator;
    }

    public void setApiAuthenticator(Authenticator apiAuthenticator)
    {
        this.apiAuthenticator = apiAuthenticator;
    }
}
