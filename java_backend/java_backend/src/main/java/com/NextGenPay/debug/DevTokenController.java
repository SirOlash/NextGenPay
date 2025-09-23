package com.NextGenPay.debug;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DevTokenController {
    private final com.NextGenPay.util.JwtAuth jwtAuth;

    @GetMapping("/dev/token/{email}")
    public Map<String,Object> token(@PathVariable String email) {
        String token = jwtAuth.generateToken(email);

        boolean verified = false;
        Map<String, Object> claims = new HashMap<>();
        try {
            // try to parse using the same signing key inside jwtAuth
            String sub = jwtAuth.extractId(token);
            verified = true;
            claims.put("sub", sub);
        } catch (Exception ex) {
            verified = false;
            claims.put("error", ex.getMessage());
        }

        Map<String,Object> resp = new HashMap<>();
        resp.put("token", token);
        resp.put("verifiedByServer", verified);
        resp.put("claims", claims);
        return resp;
    }
}

