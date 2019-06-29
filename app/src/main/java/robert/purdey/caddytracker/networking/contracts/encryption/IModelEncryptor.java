package robert.purdey.caddytracker.networking.contracts.encryption;

import robert.purdey.caddytracker.domain.encryption.EncryptModel;

public interface IModelEncryptor
{
    <TModel> EncryptModel encrypt(TModel model);
    <TModel> TModel decrypt(EncryptModel encryptModel, Class<TModel> tClass);
}
