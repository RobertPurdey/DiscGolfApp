package robert.purdey.caddytracker.domain.storage.contracts;


import androidx.annotation.NonNull;

import java.math.BigInteger;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
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

    RSAPublicKeySpec getPublicKeySpec();

    boolean isValidUserSession();

    void clearUserSession();
}
