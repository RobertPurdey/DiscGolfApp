package robert.purdey.caddytracker.domain.encryption;

public class EncryptModel
{
    public String EncryptedAesKey;
    public String EncryptedJson;

    public EncryptModel() { }

    public EncryptModel(String encryptedAesKey, String encryptedJson)
    {
        EncryptedAesKey = encryptedAesKey;
        EncryptedJson   = encryptedJson;
    }

    public String getEncryptedAesKey()
    {
        return EncryptedAesKey;
    }

    public void setEncryptedAesKey(String encryptedAesKey)
    {
        EncryptedAesKey = encryptedAesKey;
    }

    public String getEncryptedJson()
    {
        return EncryptedJson;
    }

    public void setEncryptedJson(String encryptedJson)
    {
        EncryptedJson = encryptedJson;
    }
}
