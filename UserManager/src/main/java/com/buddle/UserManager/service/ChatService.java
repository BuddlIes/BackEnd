package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.ChatRoomDto;
import com.buddle.UserManager.dto.MsgDto;
import com.buddle.UserManager.dto.SendMsgDto;
import com.buddle.UserManager.entity.ChatListInfo;
import com.buddle.UserManager.entity.MessageInfo;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.repository.ChatListRepository;
import com.buddle.UserManager.repository.MessageRepository;
import com.buddle.UserManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ChatService {
    @Autowired
    ChatListRepository chatListRepository;
    @Autowired
    MessageRepository msgRepository;
    @Autowired
    UserRepository userRepository;

    //채팅방 리스트 확인
    public List<ChatRoomDto> checkChatList(Long memberId) {
        List<ChatListInfo> chatListInfos = chatListRepository.findChatRooms(memberId);
        List<ChatRoomDto> listChatRoom = new ArrayList<>();
        for(ChatListInfo chatRoom : chatListInfos)
        {
            ChatRoomDto chatRoomDto = new ChatRoomDto();
            chatRoomDto.setChatRoomId(chatRoom.getChatRoom_id());
//            System.out.println(memberId.equals(chatRoom.getUser1_num())?chatRoom.getUser2_num():chatRoom.getUser1_num());
            Optional<UserInfo> oponentUser = userRepository.findById(memberId.equals(chatRoom.getUser1_num())?chatRoom.getUser2_num():chatRoom.getUser1_num());
            chatRoomDto.setOpponentName(oponentUser.get().getName());
            List<MessageInfo> messageInfos = msgRepository.findTopByChatroomidOrderByCreatedatDesc(chatRoom.getChatRoom_id());
            MessageInfo msg = messageInfos.get(0);

            chatRoomDto.setLastMessage(msg.getMessagecontent());
            chatRoomDto.setLastMessageTime(msg.getCreatedat());
            listChatRoom.add(chatRoomDto);
        }

        listChatRoom.sort(new Comparator<ChatRoomDto>() {
            @Override
            public int compare(ChatRoomDto o1, ChatRoomDto o2) {
//                if(o1.getLastMessageTime() <= o2.getLastMessageTime()) //time1.compareTo(time2)
                if(o1.getLastMessageTime().compareTo(o2.getLastMessageTime())<0)
                    return 1;
                else
                    return -1;
            }
        });

        return listChatRoom;
    }

    //메세지 내용 확인
    public List<MsgDto> checkMsgList(Long chatRoomId) {
        List<MessageInfo> msgInfos = msgRepository.findByChatroomidOrderByCreatedat(chatRoomId);

        List<MsgDto> msgDtoList = msgInfos.stream().map(
                m-> new MsgDto(m.getId(),m.getChatroomid(),m.getCreatedat(),m.getMessagecontent(),m.getMessagefrom())
                ).collect(Collectors.toList());

        return msgDtoList;
    }

    //메세지 전송
    public String sendMsg(SendMsgDto msg) {
        msgRepository.save(msg.toEntity());

        return "Send Completed";
    }
}
