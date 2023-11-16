package com.buddle.UserManager.dto;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Data
public class ChatRoomDto {
    private Long chatRoomId;
    private LocalDateTime lastMessageTime;
    private String opponentName;
    private String lastMessage;
}
