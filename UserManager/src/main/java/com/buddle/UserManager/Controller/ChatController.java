package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.ChatRoomDto;
import com.buddle.UserManager.Dto.MsgDto;
import com.buddle.UserManager.Dto.SendMsgDto;
import com.buddle.UserManager.Service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatController {
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
    public String getSendMsg(@RequestBody SendMsgDto reqDto)
    {
        return chatService.sendMsg(reqDto);
    }

}