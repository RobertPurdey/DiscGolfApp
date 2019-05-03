package robert.purdey.caddytracker.security.encryption;


import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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

    private final String RSA = "RSA";

    public RsaManager() throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        rsaKeyFactory = KeyFactory.getInstance(RSA);
        keyPairGen    = KeyPairGenerator.getInstance(RSA);
        cipher        = Cipher.getInstance(RSA);

        keyPairGen.initialize(2048);
    }

    @Override
    public KeyPair generateRsaKeyPair()
    {
        return keyPairGen.genKeyPair();
    }

    @Override
    public byte[] encrypt(PrivateKey privateKey, String msg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);

        return cipher.doFinal(msg.getBytes());
    }

    @Override
    public byte[] decrypt(PublicKey publicKey, byte[] encryptedMsg)
        throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException
    {
        cipher.init(Cipher.DECRYPT_MODE, publicKey);

        return cipher.doFinal(encryptedMsg);
    }

    @Override
    public byte[] encrypt(byte[] encodedPrivateKey, String msg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException
    {
        PrivateKey privateKey = rsaKeyFactory.generatePrivate(new PKCS8EncodedKeySpec(encodedPrivateKey));

        return encrypt(privateKey, msg);
    }

    @Override
    public byte[] decrypt(byte[] encodedPublicKey, byte[] encryptedMsg)
        throws InvalidKeyException, InvalidKeySpecException, BadPaddingException, IllegalBlockSizeException
    {
        PublicKey publicKey = rsaKeyFactory.generatePublic(new X509EncodedKeySpec(encodedPublicKey));

        return decrypt(publicKey, encryptedMsg);
    }
}
