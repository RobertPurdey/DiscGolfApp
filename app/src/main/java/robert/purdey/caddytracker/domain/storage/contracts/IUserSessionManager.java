package robert.purdey.caddytracker.domain.storage.contracts;

import android.support.annotation.NonNull;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.UUID;

public interface IUserSessionManager
{
    String getToken();
    void storeToken(@NonNull String token);

    String getRefreshToken();
    void storeRefreshToken(@NonNull String refreshToken);

    UUID getCurrentUserId();
    void storeCurrentUserId(@NonNull UUID id);

    byte[] getEncodedPrivateKeySpec();
    void storeEncodedPrivateKeySpec(@NonNull byte[] privateKey);

    byte[] getEncodedPublicKeySpec();
    void storeEncodedPublicKeySpec(@NonNull byte[] publicKey);

    boolean isValidUserSession();

    void clearUserSession();
}
