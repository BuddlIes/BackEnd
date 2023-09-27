package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.ChatRoomDto;
import com.buddle.UserManager.Dto.MsgDto;
import com.buddle.UserManager.Service.ChatService;
import com.buddle.UserManager.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
