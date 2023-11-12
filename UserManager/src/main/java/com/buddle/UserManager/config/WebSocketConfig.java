package com.buddle.UserManager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    //register endpoint
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") //웹소켓 서버의 엔드포인드 : /ws
                .setAllowedOrigins("*"); //모든 origin 허용
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // '/sub'가 prefix로 붙은 destination의 클라이언트에게 메시지를 보낼 수 있도록 Simple Broker 등록
        registry.enableSimpleBroker("/sub");
        // '/pub'가 prefix로 붙은 메시지들은 @MessageMapping이 붙은 method로 바운드됨
        registry.setApplicationDestinationPrefixes("/pub");
    }
}
