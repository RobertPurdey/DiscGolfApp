package robert.purdey.caddytracker.security.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import robert.purdey.caddytracker.security.contracts.IAesManager;

public class AesManager implements IAesManager
{
    private final String AES                = "AES";
    private final String CIPHER_INSTANCE    = "AES/CBC/PKCS7Padding";
    private final int IV_SIZE               = 16;
    private final int KEY_SIZE              = 16;

    private Cipher cipher;

    public AesManager() throws NoSuchAlgorithmException, NoSuchPaddingException
    {
        cipher = Cipher.getInstance(CIPHER_INSTANCE);
    }

    @Override
    public String generateKey()
    {
        // return "ROB ROB ROB ROB ";
        byte[] newKey       = new byte[KEY_SIZE];
        SecureRandom random = new SecureRandom();

        random.nextBytes(newKey);

        return Base64.getEncoder().encodeToString(newKey);
    }

    @Override
    public byte[] encrypt(String key, String msg)
        throws InvalidKeyException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException
    {
        byte[] msgBytes         = msg.getBytes();
        byte[] iv               = GetRandomizedInitVector();
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        SecretKeySpec keySpec   = new SecretKeySpec(key.getBytes(), AES);

        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParam);

        byte[] encryptedMsg = cipher.doFinal(msgBytes);

        return GetFullEncryptedMessage(iv, encryptedMsg);
    }

    private byte[] GetFullEncryptedMessage(byte[] iv, byte[] encryptedMsg)
    {
        byte[] fullEncryptedMsg = new byte[IV_SIZE + encryptedMsg.length];

        System.arraycopy(iv, 0, fullEncryptedMsg, 0, IV_SIZE);
        System.arraycopy(encryptedMsg, 0, fullEncryptedMsg, IV_SIZE, encryptedMsg.length);

        return fullEncryptedMsg;
    }

    @Override
    public String decrypt(String key, byte[] encryptedMsg)
        throws InvalidKeyException, IllegalBlockSizeException, InvalidAlgorithmParameterException, BadPaddingException
    {
        byte[] iv               = GetIvFromEncryptedMsg(encryptedMsg);
        byte[] encryptedBytes   = GetBytesFromEncryptedMsg(encryptedMsg);
        IvParameterSpec ivParam = new IvParameterSpec(iv);
        SecretKeySpec keySpec   = new SecretKeySpec(key.getBytes(), AES);

        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParam);

        byte[] decryptedMsg = cipher.doFinal(encryptedBytes);

        return new String(decryptedMsg);
    }

    private byte[] GetIvFromEncryptedMsg(byte[] encryptedMsg)
    {
        byte[] iv = new byte[IV_SIZE];
        System.arraycopy(encryptedMsg, 0, iv, 0, iv.length);

        return iv;
    }

    private byte[] GetBytesFromEncryptedMsg(byte[] encryptedMsg)
    {
        int encryptedSize       = encryptedMsg.length - IV_SIZE;
        byte[] encryptedBytes   = new byte[encryptedSize];

        System.arraycopy(encryptedMsg, IV_SIZE, encryptedBytes, 0, encryptedSize);

        return encryptedBytes;
    }

    private byte[] GetRandomizedInitVector()
    {
        byte[] randomIv     = new byte[IV_SIZE];
        SecureRandom random = new SecureRandom();

        random.nextBytes(randomIv);

        return randomIv;
    }
}
