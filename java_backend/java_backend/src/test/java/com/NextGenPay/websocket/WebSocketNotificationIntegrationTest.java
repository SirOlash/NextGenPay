package com.NextGenPay.websocket;

import com.NextGenPay.service.NotificationService;
import com.NextGenPay.util.JwtAuth;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"jwt.secret=XhD2hg8yLCBQLC9vqn7qI5TXHVRLyXZDRlBkZgoCZQc"})
public class WebSocketNotificationIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    JwtAuth jwtAuth;

    @Autowired
    NotificationService notificationService;

    private static WebSocketStompClient stompClient;

    @BeforeAll
    static void setupClient() {
        stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    @AfterAll
    static void tearDownClient() {
        if (stompClient != null) {
            stompClient.stop();
        }
    }

    @Test
    public void testWebsocket_userReceivesNotification_whenServerPushes() throws Exception {
        String username = "test@example.com";
        String token = jwtAuth.generateToken(username);

        CountDownLatch latch = new CountDownLatch(1);
        BlockingQueue<Map> messages = new LinkedBlockingQueue<>();

        WebSocketHttpHeaders wsHeaders = new WebSocketHttpHeaders();
        wsHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);

        String wsUrl = "ws://localhost:" + port + "/ws/websocket"; // no ?token param needed if using header
        CompletableFuture<StompSession> connectFuture = stompClient.connectAsync(wsUrl, wsHeaders, new StompSessionHandlerAdapter() {});
        StompSession session = connectFuture.get(5, TimeUnit.SECONDS);

        assertTrue(session.isConnected(), "Session should be connected");

        StompFrameHandler frameHandler = new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Map.class;
            }

            @Override
            @SuppressWarnings("unchecked")
            public void handleFrame(StompHeaders headers, Object payload) {
                messages.add((Map) payload);
                latch.countDown();
            }
        };

        session.subscribe("/user/queue/notifications", frameHandler);
        Thread.sleep(200);
        // 5) Act: trigger server push (this should deliver to the connected user's subscription)
        Map<String,Object> payload = Map.of(
                "type", "test",
                "msg", "hello",
                "time", Instant.now().toString()
        );
        notificationService.notifyUser(username, payload);

        // 6) Assert: wait for message and verify contents
        boolean received = latch.await(5, TimeUnit.SECONDS);
        assertTrue(received, "Expected to receive a websocket notification within 5s");
        Map receivedMsg = messages.poll(1, TimeUnit.SECONDS);
        assertNotNull(receivedMsg);
        assertEquals("hello", receivedMsg.get("msg"));

        // cleanup
        session.disconnect();
    }

}
