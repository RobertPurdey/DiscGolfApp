package robert.purdey.caddytracker.networking.contracts.encryption;

import robert.purdey.caddytracker.domain.encryption.EncryptModel;

public interface IModelEncryptor
{
    <T> EncryptModel encrypt(T model);
    <T> T decrypt(EncryptModel encryptModel, Class<T> tClass);
}
