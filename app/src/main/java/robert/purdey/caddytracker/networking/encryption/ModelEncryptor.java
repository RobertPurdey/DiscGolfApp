package robert.purdey.caddytracker.networking.encryption;

import java.util.Base64;

import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.networking.contracts.encryption.IModelEncryptor;
import robert.purdey.caddytracker.security.contracts.IAesManager;
import robert.purdey.caddytracker.security.contracts.IRsaManager;
import robert.purdey.caddytracker.security.encryption.ServerRsaPublicKeyInfo;

public class ModelEncryptor implements IModelEncryptor
{
    IRsaManager rsaManager;
    IAesManager aesManager;

    public ModelEncryptor(IRsaManager rsaManager, IAesManager aesManager)
    {
        this.rsaManager = rsaManager;
        this.aesManager = aesManager;
    }

    @Override
    public EncryptModel encrypt(String jsonModel)
    {
        String aesKey       = aesManager.generateKey();
        EncryptModel model  = null;

        byte[] encryptedKey;
        byte[] encryptedMsg;

        try
        {
            encryptedKey = rsaManager.encrypt(ServerRsaPublicKeyInfo.RSA_KEY, aesKey);
            encryptedMsg = aesManager.encrypt(aesKey, jsonModel);

            String keyBase64 = Base64.getEncoder().encodeToString(encryptedKey);
            String msgBase64 = Base64.getEncoder().encodeToString(encryptedMsg);

            model = new EncryptModel(keyBase64, msgBase64);
        }
        catch (Exception ex)
        {
            // todo: what to do?
            int x = 1;
        }

        return model;
    }

    @Override
    public String decrypt(EncryptModel encryptModel)
    {
        // todo: handle decrypt after encrypt and send works
        String jsonModel = null;

        try
        {

        }
        catch (Exception ex)
        {

        }

        return null;
    }
}
