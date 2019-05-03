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

    byte[] getEncodedPrivateKey();
    void storeEncodedPrivateKey(@NonNull byte[] privateKey);

    byte[] getEncodedPublicKey();
    void storeEncodedPublicKey(@NonNull byte[] publicKey);

    boolean isValidUserSession();

    void clearUserSession();
}
