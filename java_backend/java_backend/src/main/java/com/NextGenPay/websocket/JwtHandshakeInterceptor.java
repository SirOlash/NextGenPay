package com.NextGenPay.websocket;

import com.NextGenPay.util.JwtAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Component
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
    private static final Logger log = LoggerFactory.getLogger(JwtHandshakeInterceptor.class);
    private final JwtAuth jwtAuth;

    public JwtHandshakeInterceptor(JwtAuth jwtAuth) {
        this.jwtAuth = jwtAuth;
    }

    @Override
    public boolean beforeHandshake(ServerHttpRequest request,
                                   @NonNull ServerHttpResponse response,
                                   @NonNull WebSocketHandler wsHandler,
                                   @NonNull Map<String, Object> attributes) throws Exception {
        // 1. try Authorization header first
        List<String> authHeaders = request.getHeaders().get("Authorization");
        String token = null;
        if (authHeaders != null && !authHeaders.isEmpty()) {
            String raw = authHeaders.get(0);
            if (raw.toLowerCase().startsWith("bearer ")) {
                token = raw.substring(7).trim();
            }
        }

        // 2. fallback: try query param ?token=...
        if (token == null || token.isBlank()) {
            URI uri = request.getURI();
            MultiValueMap<String, String> params = UriComponentsBuilder.fromUri(uri).build().getQueryParams();
            token = params.getFirst("token");
        }

        if (token == null || token.isBlank()) {
            log.warn("Handshake rejected: no token provided");
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // reject handshake if no token provided
            return false;
        }

        try {
            // verify and extract user identity (we use email/subject as principal)
            String subject = jwtAuth.extractId(token); // throws if invalid/expired
            Principal principal = new StompPrincipal(subject);
            // store principal in attributes for handshake handler to pick up
            attributes.put("jwt.principal", principal);
            log.info("Handshake accepted for subject={}", subject);
//            System.out.println("Handshake accepted for: " + subject);
            return true;
        } catch (Exception ex) {
            log.warn("Handshake rejected: invalid token: {}", ex.getMessage());
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // invalid token -> reject handshake
            return false;
        }
    }

    @Override
    public void afterHandshake(@NonNull ServerHttpRequest request,
                               @NonNull ServerHttpResponse response,
                               @NonNull WebSocketHandler wsHandler,
                               Exception exception) {
        // no-op
    }
}
