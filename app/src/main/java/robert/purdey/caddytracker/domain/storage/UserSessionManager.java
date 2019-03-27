package robert.purdey.caddytracker.domain.storage;

import android.support.annotation.NonNull;

import java.util.UUID;

import robert.purdey.caddytracker.domain.storage.contracts.ISharedPreferenceManager;
import robert.purdey.caddytracker.domain.storage.contracts.IUserSessionManager;
import robert.purdey.caddytracker.utilities.Strings;

public class UserSessionManager implements IUserSessionManager
{
    private static UserSessionManager userSession = new UserSessionManager();
    private static ISharedPreferenceManager sharedPref;

    private static final String TOKEN_KEY = "com.purdey.caddytracker.token";
    private static final String REFRESH_TOKEN_KEY = "com.purdey.caddytracker.refreshToken";
    private static final String CURRENT_USER_ID_KEY = "com.purdey.caddytracker.current.user.id";

    private UserSessionManager()
    {

    }

    // todo: handle IKeyStoreManager usage
    public static UserSessionManager getInstance(ISharedPreferenceManager sharedPrefManager)
    {
        if ( sharedPref == null )
        {
            sharedPref = sharedPrefManager;
        }

        //if ( keyStore == null )
        //{
        //    keyStore = keyStoreManager;
        //}

        return userSession;
    }

    @Override
    public String getToken()
    {

        return sharedPref.getData(TOKEN_KEY);
    }

    @Override
    public void storeToken(@NonNull String token)
    {
        sharedPref.saveData(TOKEN_KEY, token);
    }

    @Override
    public String getRefreshToken()
    {
        return sharedPref.getData(REFRESH_TOKEN_KEY);
    }

    @Override
    public void storeRefreshToken(@NonNull String token)
    {
        sharedPref.saveData(REFRESH_TOKEN_KEY, token);
    }

    @Override
    public UUID getCurrentUserId()
    {
        return UUID.fromString(sharedPref.getData(CURRENT_USER_ID_KEY));
    }

    @Override
    public void storeCurrentUserId(@NonNull UUID id)
    {
        sharedPref.saveData(CURRENT_USER_ID_KEY, id.toString());
    }

    @Override
    public boolean isValidUserSession()
    {
        String token        = getToken();
        String refreshToken = getRefreshToken();

        return !Strings.isNullOrEmpty(token)
            && !Strings.isNullOrEmpty(refreshToken);
    }

    @Override
    public void clearUserSession()
    {
        storeToken("");
        storeRefreshToken("");
    }
}
