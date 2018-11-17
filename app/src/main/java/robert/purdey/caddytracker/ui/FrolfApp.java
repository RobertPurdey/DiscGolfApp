package robert.purdey.caddytracker.ui;

import android.app.Application;
import android.content.Context;

import robert.purdey.caddytracker.domain.storage.SharedPrefManager;
import robert.purdey.caddytracker.domain.storage.UserSessionManager;

public class FrolfApp extends Application
{
    private static Context mContext;
    private static UserSessionManager userSession;

    public void onCreate()
    {
        super.onCreate();

        mContext = getApplicationContext();

        userSession = UserSessionManager.getInstance(
            SharedPrefManager.getInstance(getAppContext())
        );
    }

    public static Context getAppContext()
    {
        return mContext;
    }

    public static UserSessionManager getUserSession()
    {
        return userSession;
    }
}
