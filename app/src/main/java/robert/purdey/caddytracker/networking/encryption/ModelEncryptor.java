package robert.purdey.caddytracker.networking.encryption;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.spec.RSAPrivateKeySpec;
import java.util.Base64;
import java.util.Date;

import retrofit2.converter.gson.GsonConverterFactory;
import robert.purdey.caddytracker.domain.encryption.EncryptModel;
import robert.purdey.caddytracker.networking.contracts.encryption.IModelEncryptor;
import robert.purdey.caddytracker.networking.json.DateDeserializer;
import robert.purdey.caddytracker.security.contracts.IAesManager;
import robert.purdey.caddytracker.security.contracts.IRsaManager;
import robert.purdey.caddytracker.security.encryption.ServerRsaPublicKeyInfo;
import robert.purdey.caddytracker.ui.FrolfApp;

public class ModelEncryptor implements IModelEncryptor
{
    private IRsaManager rsaManager;
    private IAesManager aesManager;

    public ModelEncryptor(IRsaManager rsaManager, IAesManager aesManager)
    {
        this.rsaManager = rsaManager;
        this.aesManager = aesManager;
    }

    @Override
    public <TModel> EncryptModel encrypt(TModel modelToEncrypt)
    {
        String aesKey       = aesManager.generateKey();
        EncryptModel model  = null;

        try
        {
            String jsonModel = new Gson().toJson(modelToEncrypt);

            byte[] encryptedKey    = rsaManager.encrypt(ServerRsaPublicKeyInfo.RSA_KEY, aesKey);
            byte[] encryptedMsg    = aesManager.encrypt(aesKey, jsonModel);
            Base64.Encoder encoder = Base64.getEncoder();

            String keyBase64 = encoder.encodeToString(encryptedKey);
            String msgBase64 = encoder.encodeToString(encryptedMsg);

            model = new EncryptModel(keyBase64, msgBase64);
        }
        catch (Exception ex) { }

        return model;
    }

    @Override
    public <TModel> TModel decrypt(EncryptModel encryptModel, Class<TModel> tClass)
    {
        String decryptedJson = "";

        try
        {
            RSAPrivateKeySpec privKeySpec = FrolfApp.getUserSession().getPrivateKeySpec();
            Base64.Decoder decoder        = Base64.getDecoder();

            byte[] encryptedAesKeyBytes = decoder.decode(encryptModel.getEncryptedAesKey());
            String decryptedAesKeyBas64 = rsaManager.decrypt(privKeySpec, encryptedAesKeyBytes);

            byte[] encryptedJsonBytes   = decoder.decode(encryptModel.EncryptedJson);
            decryptedJson               = aesManager.decrypt(decryptedAesKeyBas64, encryptedJsonBytes);
        }
        catch (Exception ex) { }

        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new DateDeserializer()).create();
        return gson.fromJson(decryptedJson, tClass);
    }
}
