package com.buddle.UserManager.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ChatRoomDto {
    private Long chatRoomId;
    private Long lastMessageTime;
    private String opponentName;
    private String lastMessage;

}