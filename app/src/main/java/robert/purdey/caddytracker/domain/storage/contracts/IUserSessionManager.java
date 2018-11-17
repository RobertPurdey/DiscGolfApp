package robert.purdey.caddytracker.domain.storage.contracts;

import android.support.annotation.NonNull;

public interface IUserSessionManager
{
    String getToken();
    void storeToken(@NonNull String token);

    String getRefreshToken();
    void storeRefreshToken(@NonNull String refreshToken);

    boolean isValidUserSession();

    void clearUserSession();
}
