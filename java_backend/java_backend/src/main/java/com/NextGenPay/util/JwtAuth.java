package com.NextGenPay.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.security.Key;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuth {

    @Value("${jwt.secret:}") // accept empty if not set
    private String jwtSecretFromConfig;

    private Key signingKey;
    private String base64Secret; // useful for tests/dev

    @PostConstruct
    public void init() {
        try {
            byte[] keyBytes;

            if (jwtSecretFromConfig != null && !jwtSecretFromConfig.trim().isEmpty()) {
                // try to decode as Base64 first
                try {
                    keyBytes = Decoders.BASE64.decode(jwtSecretFromConfig.trim());
                } catch (Exception ex) {
                    // if it's not valid Base64, fall back to raw bytes of the string
                    keyBytes = jwtSecretFromConfig.getBytes(java.nio.charset.StandardCharsets.UTF_8);
                }

                // if too short (< 32 bytes), derive a 32-byte key via SHA-256
                if (keyBytes.length < 32) {
                    MessageDigest md = MessageDigest.getInstance("SHA-256");
                    keyBytes = md.digest(keyBytes); // 32 bytes now
                }

                base64Secret = Base64.getEncoder().encodeToString(keyBytes);

            } else {
                // generate secure random 32 bytes
                byte[] random = new byte[32];
                new SecureRandom().nextBytes(random);
                keyBytes = random;
                base64Secret = Base64.getEncoder().encodeToString(random);
                System.out.println("WARNING: No jwt.secret provided — using ephemeral generated secret (dev only).");
            }

            signingKey = Keys.hmacShaKeyFor(keyBytes);

        } catch (Exception e) {
            throw new IllegalStateException("Failed to initialize JwtAuth signing key", e);
        }
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        long now = System.currentTimeMillis();
        long expiry = now + (1000L * 60 * 60); // 1 hour
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(expiry))
                .signWith(signingKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractId(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    private Date extractExpiration(String token) {
        return Jwts.parserBuilder().setSigningKey(signingKey).build().parseClaimsJws(token).getBody().getExpiration();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, String id) {
        final String extractedId = extractId(token);
        return (extractedId.equals(id) && !isTokenExpired(token));
    }

    public String getBase64Secret() {
        return base64Secret;
    }
}
