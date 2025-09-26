package com.NextGenPay.controller;

import com.NextGenPay.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/debug")
public class DebugPushController {
    private final NotificationService notify;

    @PostMapping("/push-user")
    public ResponseEntity<Void> pushUser(@RequestBody Map<String,String> body) {
        String user = body.get("user");
        String msg = body.get("msg");
        notify.notifyUser(user, Map.of("text", msg, "time", Instant.now().toString()));
        return ResponseEntity.ok().build();
    }
}
