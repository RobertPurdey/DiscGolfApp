package robert.purdey.caddytracker.networking.contracts.encryption;

import robert.purdey.caddytracker.domain.encryption.EncryptModel;

public interface IModelEncryptor
{
    EncryptModel encrypt(String msg);
    String decrypt(EncryptModel encryptModel);
}
