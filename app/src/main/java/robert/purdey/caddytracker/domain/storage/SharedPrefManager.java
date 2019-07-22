package robert.purdey.caddytracker.domain.storage;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

import robert.purdey.caddytracker.domain.storage.contracts.ISharedPreferenceManager;

public class SharedPrefManager implements ISharedPreferenceManager
{
    private static SharedPrefManager sharedPrefManager = new SharedPrefManager();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharedPrefManager()
    {

    }

    public static SharedPrefManager getInstance(Context context)
    {
        if (sharedPreferences == null)
        {
            sharedPreferences = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
            editor            = sharedPreferences.edit();
        }

        return sharedPrefManager;
    }

    // todo: allow storing as various data types (more than just String)
    @Override
    public void saveData(@NonNull String key, @NonNull String data)
    {
        editor.putString(key, data);
        editor.commit();
    }

    @Override
    public String getData(@NonNull String key)
    {
        return sharedPreferences.getString(key, "");
    }

    @Override
    public void removeData(@NonNull String key) {
        editor.remove(key);
        editor.commit();
    }
}
