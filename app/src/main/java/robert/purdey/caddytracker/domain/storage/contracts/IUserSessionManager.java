package robert.purdey.caddytracker.domain.storage.contracts;

import android.support.annotation.NonNull;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.UUID;

public interface IUserSessionManager
{
    String getToken();
    void storeToken(@NonNull String token);

    String getRefreshToken();
    void storeRefreshToken(@NonNull String refreshToken);

    UUID getCurrentUserId();
    void storeCurrentUserId(@NonNull UUID id);

    BigInteger getRsaModulus();
    void storeRsaModulus(@NonNull BigInteger modulus);

    BigInteger getRsaPublicExponent();
    void storeRsaPublicExponent(@NonNull BigInteger pubExponent);

    BigInteger getRsaPrivateExponent();
    void storeRsaPrivateExponent(@NonNull BigInteger privExponent);

    RSAPrivateKeySpec getPrivateKeySpec();

    boolean isValidUserSession();

    void clearUserSession();
}
