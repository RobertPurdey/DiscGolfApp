package robert.purdey.caddytracker.security.contracts;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

public interface IRsaManager
{
    KeyPair generateRsaKeyPair();

    byte[] encrypt(RSAPublicKey pkey, String msg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    String decrypt(RSAPrivateKey publicKey, byte[] encryptedMsg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException;

    byte[] encrypt(byte[] pkey, String msg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;

    String decrypt(byte[] publicKey, byte[] encryptedMsg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;

    byte[] encrypt(RSAPublicKeySpec publicKeySpec, String msg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;

    String decrypt(RSAPrivateKeySpec privateKeySpec, byte[] encryptedMsg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException;
}
