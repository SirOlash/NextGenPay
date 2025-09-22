package com.NextGenPay.debug;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class DevTokenController {
    private final com.NextGenPay.util.JwtAuth jwtAuth;

    @GetMapping("/dev/token/{email}")
    public Map<String,String> token(@PathVariable String email) {
        String t = jwtAuth.generateToken(email);
        return Map.of("token", t);
    }
}
