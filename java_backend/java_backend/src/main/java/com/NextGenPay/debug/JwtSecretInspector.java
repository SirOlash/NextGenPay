package com.NextGenPay.debug;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.util.Base64;

@Component
public class JwtSecretInspector {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostConstruct
    public void inspect() {
        try {
            String trimmed = jwtSecret == null ? "" : jwtSecret.trim();
            byte[] keyBytes = Base64.getDecoder().decode(trimmed);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(keyBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            System.out.println(">>> JWT secret present. Decoded length = " + keyBytes.length + " bytes. SHA256 fingerprint = " + sb);
        } catch (Exception e) {
            System.out.println(">>> JWT secret missing/invalid base64: " + e.getMessage());
        }
    }
}
