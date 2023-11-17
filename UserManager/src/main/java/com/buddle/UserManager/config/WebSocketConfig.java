package com.buddle.UserManager.config;

import com.buddle.UserManager.interceptor.WebSocketInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private final WebSocketInterceptor webSocketInterceptor;

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //메세지 구동하는 요청 url (메세지 받을 때)
        config.enableSimpleBroker("/sub");
        //메세지 발행하는 요청 url (메세지 보낼 때)
        config.setApplicationDestinationPrefixes("/pub");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //stomp 접속 주소 url => /ws-stomp
        // 연결 URL : ws://localhost:8080/ws-stomp/websocket
        registry.addEndpoint("/ws-stomp")
                // .setAllowedOrigins("http://localhost:3000") // "http://localhost:3000" 페이지로부터의 요청만 허용
                .setAllowedOriginPatterns("**") // 전체 페이지로부터의 요청 허용
                .setAllowedOrigins("**")
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(webSocketInterceptor);
    }
}
