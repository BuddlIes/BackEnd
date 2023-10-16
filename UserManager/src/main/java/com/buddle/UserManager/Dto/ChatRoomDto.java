package com.buddle.UserManager.Dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto {
    private Long chatRoomId;
    private Long lastMessageTime;
    private String oponentName;
    private String lastMessage;

}
