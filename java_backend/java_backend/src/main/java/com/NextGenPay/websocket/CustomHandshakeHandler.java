package com.NextGenPay.websocket;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.lang.NonNull;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class CustomHandshakeHandler extends DefaultHandshakeHandler {

    @Override
    protected Principal determineUser(@NonNull ServerHttpRequest request,
                                      @NonNull WebSocketHandler wsHandler,
                                      @NonNull Map<String, Object> attributes) {
        Object p = attributes.get("jwt.principal");
        if (p instanceof Principal) {
            return (Principal) p;
        }
        return super.determineUser(request, wsHandler, attributes);
    }
}
