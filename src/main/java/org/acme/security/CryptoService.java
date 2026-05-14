package org.acme.security;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CryptoService {

    @ConfigProperty(name = "app.crypto.key")
    String keyPath;

    private SecretKeySpec secretKey;

    private static final int IV_LENGTH = 12;
    private static final int TAG_LENGTH = 128;

    @PostConstruct
    void init() {
        try {

            String key = Files.readString(
                    Path.of(keyPath)
            ).trim();

            secretKey = new SecretKeySpec(
                    key.getBytes(StandardCharsets.UTF_8),
                    "AES"
            );

        } catch (Exception e) {
            throw new RuntimeException("Erro ao carregar chave AES", e);
        }
    }

    public String encrypt(String value) {

        try {

            byte[] iv = new byte[IV_LENGTH];

            new SecureRandom().nextBytes(iv);

            Cipher cipher = Cipher.getInstance(
                    "AES/GCM/NoPadding"
            );

            GCMParameterSpec spec =
                    new GCMParameterSpec(
                            TAG_LENGTH,
                            iv
                    );

            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    secretKey,
                    spec
            );

            byte[] encrypted =
                    cipher.doFinal(
                            value.getBytes(StandardCharsets.UTF_8)
                    );

            byte[] result =
                    new byte[iv.length + encrypted.length];

            System.arraycopy(
                    iv,
                    0,
                    result,
                    0,
                    iv.length
            );

            System.arraycopy(
                    encrypted,
                    0,
                    result,
                    iv.length,
                    encrypted.length
            );

            return Base64.getEncoder()
                    .encodeToString(result);

        } catch (Exception e) {
            throw new RuntimeException(
                    "Erro ao criptografar",
                    e
            );
        }
    }

    public String decrypt(String value) {

        try {

            byte[] decoded =
                    Base64.getDecoder()
                            .decode(value);

            byte[] iv =
                    new byte[IV_LENGTH];

            byte[] encrypted =
                    new byte[
                            decoded.length - IV_LENGTH
                            ];

            System.arraycopy(
                    decoded,
                    0,
                    iv,
                    0,
                    IV_LENGTH
            );

            System.arraycopy(
                    decoded,
                    IV_LENGTH,
                    encrypted,
                    0,
                    encrypted.length
            );

            Cipher cipher =
                    Cipher.getInstance(
                            "AES/GCM/NoPadding"
                    );

            GCMParameterSpec spec =
                    new GCMParameterSpec(
                            TAG_LENGTH,
                            iv
                    );

            cipher.init(
                    Cipher.DECRYPT_MODE,
                    secretKey,
                    spec
            );

            byte[] decrypted =
                    cipher.doFinal(encrypted);

            return new String(
                    decrypted,
                    StandardCharsets.UTF_8
            );

        } catch (Exception e) {

            throw new RuntimeException(
                    "Erro ao descriptografar",
                    e
            );
        }
    }
}