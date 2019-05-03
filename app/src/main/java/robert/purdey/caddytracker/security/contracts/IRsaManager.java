package robert.purdey.caddytracker.security.contracts;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface IRsaManager
{
    KeyPair generateRsaKeyPair();

    byte[] encrypt(PrivateKey pkey, String msg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    byte[] decrypt(PublicKey publicKey, byte[] encryptedMsg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    byte[] encrypt(byte[] pkey, String msg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;

    byte[] decrypt(byte[] publicKey, byte[] encryptedMsg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;
}
