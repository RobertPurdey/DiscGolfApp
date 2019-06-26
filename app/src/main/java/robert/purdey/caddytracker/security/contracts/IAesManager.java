package robert.purdey.caddytracker.security.contracts;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface IAesManager
{
    String generateKey();

    byte[] encrypt(String key, String msg)
        throws InvalidKeyException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException;

    String decrypt(String key, byte[] encryptedMsg)
        throws InvalidKeyException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException;
}
