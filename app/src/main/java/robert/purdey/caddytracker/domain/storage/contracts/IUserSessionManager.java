package robert.purdey.caddytracker.domain.storage.contracts;

import android.support.annotation.NonNull;

import java.util.UUID;

public interface IUserSessionManager
{
    String getToken();
    void storeToken(@NonNull String token);

    String getRefreshToken();
    void storeRefreshToken(@NonNull String refreshToken);

    UUID getCurrentUserId();
    void storeCurrentUserId(@NonNull UUID id);

    boolean isValidUserSession();

    void clearUserSession();
}
