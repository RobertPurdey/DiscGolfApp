package robert.purdey.caddytracker.domain.storage;

import androidx.annotation.NonNull;

import java.math.BigInteger;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.UUID;

import robert.purdey.caddytracker.domain.storage.contracts.ISharedPreferenceManager;
import robert.purdey.caddytracker.domain.storage.contracts.IUserSessionManager;
import robert.purdey.caddytracker.utilities.Strings;

public class UserSessionManager implements IUserSessionManager
{
    private static UserSessionManager userSession = new UserSessionManager();
    private static ISharedPreferenceManager sharedPref;

    private static final String TOKEN_KEY               = "com.purdey.caddytracker.token";
    private static final String REFRESH_TOKEN_KEY       = "com.purdey.caddytracker.refreshToken";
    private static final String CURRENT_USER_ID_KEY     = "com.purdey.caddytracker.current.user.id";
    private static final String KEY_MODULUS             = "com.purdey.caddytracker.keyModulus";
    private static final String PUBLIC_KEY_EXPONENT     = "com.purdey.caddytracker.publicKeyExponent";
    private static final String PRIVATE_KEY_EXPONENT    = "com.purdey.caddytracker.privateKeyExponent";

    private UserSessionManager()
    {

    }

    // todo: handle IKeyStoreManager usage
    public static UserSessionManager getInstance(ISharedPreferenceManager sharedPrefManager)
    {
        if ( sharedPref == null )
        {
            sharedPref = sharedPrefManager;
        }

        return userSession;
    }

    @Override
    public String getToken()
    {

        return sharedPref.getData(TOKEN_KEY);
    }

    @Override
    public void storeToken(@NonNull String token)
    {
        sharedPref.saveData(TOKEN_KEY, token);
    }

    @Override
    public String getRefreshToken()
    {
        return sharedPref.getData(REFRESH_TOKEN_KEY);
    }

    @Override
    public void storeRefreshToken(@NonNull String token)
    {
        sharedPref.saveData(REFRESH_TOKEN_KEY, token);
    }

    @Override
    public UUID getCurrentUserId()
    {
        return UUID.fromString(sharedPref.getData(CURRENT_USER_ID_KEY));
    }

    @Override
    public void storeCurrentUserId(@NonNull UUID id)
    {
        sharedPref.saveData(CURRENT_USER_ID_KEY, id.toString());
    }

    @Override
    public BigInteger getRsaModulus()
    {
        String modBase64 = sharedPref.getData(KEY_MODULUS);
        byte[] modBytes  = Base64.getDecoder().decode(modBase64);

        return new BigInteger(modBytes);
    }

    @Override
    public void storeRsaModulus(@NonNull BigInteger modulus)
    {
        byte[] modBytes  = modulus.toByteArray();
        String modBase64 = Base64.getEncoder().encodeToString(modBytes);

        sharedPref.saveData(KEY_MODULUS, modBase64);
    }

    @Override
    public BigInteger getRsaPublicExponent()
    {
        String pubBase64 = sharedPref.getData(PUBLIC_KEY_EXPONENT);
        byte[] pubBytes  = Base64.getDecoder().decode(pubBase64);

        return new BigInteger(pubBytes);
    }

    @Override
    public void storeRsaPublicExponent(@NonNull BigInteger pubExponent)
    {
        byte[] pubExponentBytes  = pubExponent.toByteArray();
        String pubExponentBase64 = Base64.getEncoder().encodeToString(pubExponentBytes);

        sharedPref.saveData(PUBLIC_KEY_EXPONENT, pubExponentBase64);
    }

    @Override
    public BigInteger getRsaPrivateExponent()
    {
        String privBase64 = sharedPref.getData(PRIVATE_KEY_EXPONENT);
        byte[] privBytes  = Base64.getDecoder().decode(privBase64);

        return new BigInteger(privBytes);
    }

    @Override
    public void storeRsaPrivateExponent(@NonNull BigInteger privExponent)
    {
        byte[] privExponentBytes  = privExponent.toByteArray();
        String privExponentBase64 = Base64.getEncoder().encodeToString(privExponentBytes);

        sharedPref.saveData(PRIVATE_KEY_EXPONENT, privExponentBase64);
    }

    @Override
    public RSAPrivateKeySpec getPrivateKeySpec()
    {
        return new RSAPrivateKeySpec( getRsaModulus(), getRsaPrivateExponent() );
    }

    @Override
    public RSAPublicKeySpec getPublicKeySpec()
    {
        return new RSAPublicKeySpec( getRsaModulus(), getRsaPublicExponent() );
    }

    @Override
    public boolean isValidUserSession()
    {
        String token        = getToken();
        String refreshToken = getRefreshToken();

        return !Strings.isNullOrEmpty(token)
            && !Strings.isNullOrEmpty(refreshToken);
    }

    @Override
    public void clearUserSession()
    {
        storeToken("");
        storeRefreshToken("");
    }
}
