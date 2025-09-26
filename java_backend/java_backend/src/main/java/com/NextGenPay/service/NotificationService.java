package com.NextGenPay.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private final SimpMessagingTemplate template;

    public void notifyUser(String username, Object payload) {
        log.debug("notifyUser username={} payload={}", username, payload);
//        System.out.println("Sending to user=" + username + " payload=" + payload);
        template.convertAndSendToUser(username, "/queue/notifications", payload);
    }

    public void notifyMall(String mallId, Object payload) {
        template.convertAndSend("/topic/mall-" + mallId, payload);
    }
}
