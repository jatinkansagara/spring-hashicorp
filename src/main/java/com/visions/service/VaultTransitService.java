package com.visions.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

@Service
public class VaultTransitService {

    private final VaultOperations vaultOperations;

    @Value("${vault.transit.key-name}")
    private String keyName;

    public VaultTransitService(VaultOperations vaultOperations){
        this.vaultOperations = vaultOperations;
    }

    public String encryptData(String plainText){
        // Sends plaintext to Vault and returns the Vault-generated ciphertext
        Ciphertext ciphertext = vaultOperations.opsForTransit()
                .encrypt(keyName, Plaintext.of(plainText));
        return ciphertext.getCiphertext();
    }

    public String decryptData(String cipherText){
        // Sends ciphertext to Vault and returns the original plaintext
        Plaintext plaintext = vaultOperations.opsForTransit()
                .decrypt(keyName, Ciphertext.of(cipherText));
        return plaintext.asString();
    }
}
