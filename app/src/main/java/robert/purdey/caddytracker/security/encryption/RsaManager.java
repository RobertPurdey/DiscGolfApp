package robert.purdey.caddytracker.security.encryption;


import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import robert.purdey.caddytracker.security.contracts.IRsaManager;


public class RsaManager implements IRsaManager
{
    private KeyFactory rsaKeyFactory;
    private KeyPairGenerator keyPairGen;
    private Cipher cipher;

    private final String RSA             = "RSA";
    private final String CIPHER_INSTANCE = "RSA/ECB/PKCS1PADDING";

    public RsaManager() throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        rsaKeyFactory = KeyFactory.getInstance(RSA);
        keyPairGen    = KeyPairGenerator.getInstance(RSA);
        cipher        = Cipher.getInstance(CIPHER_INSTANCE);

        keyPairGen.initialize(2048);
    }

    @Override
    public KeyPair generateRsaKeyPair()
    {
        return keyPairGen.genKeyPair();
    }

    @Override
    public byte[] encrypt(RSAPublicKey publicKey, String msg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {

        byte[] msgBytes = msg.getBytes();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(msgBytes);
    }

    @Override
    public String decrypt(RSAPrivateKey privateKey, byte[] encryptedMsg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(cipher.doFinal(encryptedMsg));
    }

    @Override
    public byte[] encrypt(byte[] encodedPublicKey, String msg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException
    {
        RSAPublicKey publicKey = (RSAPublicKey)rsaKeyFactory.generatePublic(new X509EncodedKeySpec(encodedPublicKey));

        return encrypt(publicKey, msg);
    }

    @Override
    public String decrypt(byte[] encodedPrivateKey, byte[] encryptedMsg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException
    {
        RSAPrivateKey privateKey = (RSAPrivateKey)rsaKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));

        return decrypt(privateKey, encryptedMsg);
    }

    @Override
    public byte[] encrypt(RSAPublicKeySpec publicKeySpec, String msg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException
    {
        RSAPublicKey publicKey = (RSAPublicKey)rsaKeyFactory.generatePublic(publicKeySpec);

        return encrypt(publicKey, msg);
    }

    @Override
    public String decrypt(RSAPrivateKeySpec privateKeySpec, byte[] encryptedMsg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException
    {
        RSAPrivateKey privateKey = (RSAPrivateKey)rsaKeyFactory.generatePrivate(privateKeySpec);

        return decrypt(privateKey, encryptedMsg);
    }
}
