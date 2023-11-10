package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.ChatRoomDto;
import com.buddle.UserManager.dto.MsgDto;
import com.buddle.UserManager.dto.SendMsgDto;
import com.buddle.UserManager.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//web socket STOMP 사용
@RestController
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate simpMessagingTemplate;
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

    @PostMapping("/chat/send_message")
    public void sendMsg(@RequestBody SendMsgDto reqDto, SimpMessageHeaderAccessor accessor)
    {
        simpMessagingTemplate.convertAndSend("/sub/chat/send_message" + reqDto.getChatroomid(), reqDto);

//        return chatService.sendMsg(reqDto);
    }
//    @PostMapping("/chat/send_message")
//    public String sendMsg(@RequestBody SendMsgDto reqDto)
//    {
//        return chatService.sendMsg(reqDto);
//    }

}
