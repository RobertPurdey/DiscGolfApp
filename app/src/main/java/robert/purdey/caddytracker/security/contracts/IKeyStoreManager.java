package robert.purdey.caddytracker.security.contracts;

import javax.crypto.SecretKey;

/**
 * Defines access to the Android keystore.
 */
public interface IKeyStoreManager
{
    SecretKey storeSymmetricKey(String alias);
    SecretKey getSymmetricKey(String alias);
    void removeKey(String alias);
}
