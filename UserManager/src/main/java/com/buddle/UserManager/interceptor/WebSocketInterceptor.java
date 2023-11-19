package com.buddle.UserManager.interceptor;

import com.buddle.UserManager.security.TokenProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import javax.security.auth.message.AuthException;

@Slf4j
@Component
public class WebSocketInterceptor implements ChannelInterceptor {

    private final TokenProvider tokenProvider;

    @Autowired
    public WebSocketInterceptor(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @SneakyThrows
    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            String authToken = accessor.getFirstNativeHeader("auth-token");

//            if (!"spring-chat-auth-token".equals(authToken)) {
//                System.out.println("xxxxxxxxxxaabb");
//                throw new AuthException("fail");
//
//            }
            // Validate the JWT token using the TokenProvider
            if (authToken != null && tokenProvider.validateAndGetUserId(authToken) != null) {
                // Token is valid, you can proceed with the connection
                // You may also want to extract user information from the token if needed
                String userId = tokenProvider.validateAndGetUserId(authToken);
                System.out.println("Valid JWT token for user(userId): " + userId);
                System.out.println("Valid JWT token for user(authToken): " + authToken);
                System.out.println("Valid JWT token for user(authToken): " + tokenProvider.validateAndGetUserId(authToken));
            } else {
                // Invalid token, reject the connection
                System.out.println("Invalid JWT token");
                System.out.println("InValid JWT token for user(authToken): " + authToken);
                System.out.println("InValid JWT token for user(authToken): " + tokenProvider.validateAndGetUserId(authToken));
                throw new AuthException("fail");
            }
        }

        return message;
    }
}