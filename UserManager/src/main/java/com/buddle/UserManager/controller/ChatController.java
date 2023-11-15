package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.ChatMessage;
import com.buddle.UserManager.dto.ChatRoomDto;
import com.buddle.UserManager.dto.MsgDto;
import com.buddle.UserManager.dto.SendMsgDto;
import com.buddle.UserManager.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//web socket STOMP 사용
@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {
    private static final Set<String> SESSION_IDS = new HashSet<>();
    private final SimpMessagingTemplate messagingTemplate;

//    private final SimpMessagingTemplate simpMessagingTemplate;
    @Autowired
    ChatService chatService;

    @GetMapping("/chat/get_room_list")
    public List<ChatRoomDto> getChatRoomList(@RequestParam Long memberId)
    {
        return chatService.checkChatList(memberId);
    }

    @GetMapping("/chat/get_message_list")
    public List<MsgDto> getMessageList(@RequestParam Long chatRoomId)
    {
        return chatService.checkMsgList(chatRoomId);
    }

//    @MessageMapping("/chat/send_message")
//    public void sendMsg(@RequestBody SendMsgDto reqDto, SimpMessageHeaderAccessor accessor)
//    {
//        simpMessagingTemplate.convertAndSend("/sub/chat/send_message" + reqDto.getChatroomid(), reqDto);
//
////        return chatService.sendMsg(reqDto);
//    }



    @PostMapping("/chat/send_message")
    public String sendMsg(@RequestBody SendMsgDto reqDto)
    {
        return chatService.sendMsg(reqDto);
    }



    @MessageMapping("/chat") // "/pub/chat"
    public void publishChat(ChatMessage chatMessage) {
        log.info("publishChat : {}", chatMessage);

        messagingTemplate.convertAndSend("/sub/chat/" + chatMessage.getRoomSeq(), chatMessage);
    }

    @EventListener(SessionConnectEvent.class)
    public void onConnect(SessionConnectEvent event) {
        String sessionId = event.getMessage().getHeaders().get("simpSessionId").toString();
        SESSION_IDS.add(sessionId);
        log.info("[connect] connections : {}", SESSION_IDS.size());
    }

    @EventListener(SessionDisconnectEvent.class)
    public void onDisconnect(SessionDisconnectEvent event) {
        String sessionId = event.getSessionId();
        SESSION_IDS.remove(sessionId);
        log.info("[disconnect] connections : {}", SESSION_IDS.size());
    }
}
