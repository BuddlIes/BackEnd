package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.ChatRoomDto;
import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Entity.ChatListInfo;
import com.buddle.UserManager.Entity.MessageInfo;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.ChatListRepository;
import com.buddle.UserManager.Repository.MessageRepository;
import com.buddle.UserManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
            Optional<UserInfo> oponentUser = userRepository.findById(memberId==chatRoom.getUser1_num()?chatRoom.getUser2_num():chatRoom.getUser1_num());
            chatRoomDto.setOponentName(oponentUser.get().getName());
            List<MessageInfo> messageInfos = msgRepository.findTopByChatroomidOrderByCreatedatDesc(chatRoom.getChatRoom_id());
            MessageInfo msg = messageInfos.get(0);

//            Optional<MessageInfo> messageInfo = msgRepository.findByChatId(chatRoom.getChatRoom_id());
            chatRoomDto.setLastMessage(msg.getMessagecontent());
            chatRoomDto.setLastMessageTime(msg.getCreatedat());
            listChatRoom.add(chatRoomDto);
        }

        listChatRoom.sort(new Comparator<ChatRoomDto>() {
            @Override
            public int compare(ChatRoomDto o1, ChatRoomDto o2) {
                if(o1.getLastMessageTime() <= o2.getLastMessageTime())
                    return -1;
                else
                    return 1;
            }
        });
        return listChatRoom;
//        Optional<ChatListInfo> optChatRoomId = chatListRepository.findByRoomId();
//        Optional<ChatListInfo> optUser1 = chatListRepository.findByUser1(users.getUser_number());
//        Optional<ChatListInfo> optUser2 = chatListRepository.findByUser2(users.getUser_number());
//
//        Optional<MessageInfo> optChatId = msgRepository.findByChatId(users.getUser_number());
//        Optional<MessageInfo> optMsgId = msgRepository.findByMsgId(users.getUser_number());
//        Optional<MessageInfo> optMsgContent = msgRepository.findByMsgContent(users.getUser_number());
//        Optional<MessageInfo> optTime = msgRepository.findByCreatedTime(users.getUser_number());
//
//        if(optUserId.isEmpty() && optUserEmail.isEmpty() && optWallet.isEmpty() && optNickname.isEmpty()){ //같은 게 없으면
//            userRepository.save(users.toEntity());
//            return "Join Completed";
//        }
//        else {
//            return "Cannot Join";
//        }
    }

    //메세지 내용 확인
//    public String checkMsgContent(UserJoinRequestDto users) {
//
//    }
//
//    //메세지 전송
//    public String sendMsg(UserJoinRequestDto users) {
//
//    }
}
