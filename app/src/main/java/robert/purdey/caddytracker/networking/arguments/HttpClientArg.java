package robert.purdey.caddytracker.networking.arguments;

import java.util.Map;

/**
 * Information required to build an Http Client.
 */
public class HttpClientArg
{
    private int readTimeout;
    private int connectionTimeout;
    private String baseUrl;
    private Map <String, String> headers;

    public HttpClientArg(
        int readTimeout,
        int connectionTimeout,
        String baseUrl,
        Map<String, String> headers)
    {
        this.readTimeout         = readTimeout;
        this.connectionTimeout   = connectionTimeout;
        this.baseUrl             = baseUrl;
        this.headers             = headers;
    }

    public int getReadTimeout()
    {
        return readTimeout;
    }

    public int getConnectionTimeout()
    {
        return connectionTimeout;
    }

    public Map<String, String> getHeaders()
    {
        return headers;
    }
}
