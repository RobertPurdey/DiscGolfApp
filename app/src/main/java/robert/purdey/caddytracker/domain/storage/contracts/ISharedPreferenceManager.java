package robert.purdey.caddytracker.domain.storage.contracts;


public interface ISharedPreferenceManager
{
    void saveData(String key, String data);
    String getData(String key);
    void removeData(String key);
}
