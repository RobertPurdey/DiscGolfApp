package robert.purdey.caddytracker.security;

import android.content.Context;
import android.support.annotation.NonNull;
import com.yakivmospan.scytale.Store;
import javax.crypto.SecretKey;
import robert.purdey.caddytracker.security.contracts.IKeyStoreManager;

/**
 This class uses Scytale https://github.com/yakivmospan/scytale to access the android
 KeyStore. The main benefit being the automatic handling of various android API versions
 and reduction of boilerplate code.

 ********************************************************************************************
 Copyright 2016 Yakiv Mospan

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ********************************************************************************************
 */
public class KeyStoreManager implements IKeyStoreManager
{
    private Store appKeyStore;

    public KeyStoreManager(Context appContext)
    {
        appKeyStore = new Store(appContext);
    }

    /**
     * Stores a generated symmetric key for the given key alias.
     *
     * @param alias - the name associated with the key stored.
     *
     * @return The generated symmetric key associated with the alias or null if any errors
     * occurred.
     */
    @Override
    public SecretKey storeSymmetricKey(@NonNull String alias)
    {
        SecretKey key = null;

        if ( !appKeyStore.hasKey(alias) )
        {
            key = appKeyStore.generateSymmetricKey(alias, null);
        }

        return key;
    }

    /**
     * Gets the SecretKey associated with the provided alias.
     *
     * @param alias - alias associated with the key being requested
     *
     * @return The generated symmetric key associated with the alias or null if any errors
     * occurred using the Scytale functionality.
     */
    @Override
    public SecretKey getSymmetricKey(@NonNull String alias)
    {
        return appKeyStore.getSymmetricKey(alias, null);
    }

    /**
     * Removes a stored key from the KeyStore
     *
     * @param alias - the name associated with the key to be removed.
     */
    @Override
    public void removeKey(@NonNull String alias)
    {
        appKeyStore.deleteKey(alias);
    }
}
