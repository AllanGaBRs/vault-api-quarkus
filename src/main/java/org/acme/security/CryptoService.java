package org.acme.security;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CryptoService {

    @ConfigProperty(name = "app.crypto.key")
    String keyPath;

    private SecretKeySpec secretKey;

    @PostConstruct
    void init() {
        try {
            String key = Files.readString(Path.of(keyPath))
                    .trim();

            secretKey = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    "AES"
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar chave AES.");
        }
    }

    public String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] encrypted = cipher.doFinal(
                    value.getBytes(StandardCharsets.UTF_8)
            );

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar.");
        }
    }

    public String decrypt(String encryptedValue) {
        try {
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] decoded = Base64.getDecoder().decode(encryptedValue);

            byte[] decrypted = cipher.doFinal(decoded);

            return new String(decrypted, StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar.");
        }
    }
}