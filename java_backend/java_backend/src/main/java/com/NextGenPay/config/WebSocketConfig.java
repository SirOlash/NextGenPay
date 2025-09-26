package com.NextGenPay.config;

import com.NextGenPay.websocket.CustomHandshakeHandler;
import com.NextGenPay.websocket.JwtHandshakeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private JwtHandshakeInterceptor jwtHandshakeInterceptor;

    /**
     * Configure message broker: enable a simple in-memory broker for topics/queues
     * and set application prefix for messages sent from clients to @MessageMapping methods.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        // messages to destinations starting with /topic or /queue will be handled by the broker
        config.enableSimpleBroker("/topic", "/queue");

        // client -> server messages should be prefixed with /app
        config.setApplicationDestinationPrefixes("/app");

        // prefix for user destinations (send to a specific user): /user/{id}/...
        config.setUserDestinationPrefix("/user/");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry
                .addEndpoint("/ws")
                .setHandshakeHandler(new CustomHandshakeHandler())
                .addInterceptors(jwtHandshakeInterceptor)
                .setAllowedOriginPatterns("*") //remove during production
                .withSockJS();
    }
}
