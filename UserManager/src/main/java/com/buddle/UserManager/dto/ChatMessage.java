package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage {
    public enum MessageType{
        ENTER, TALK, LEAVE;
    }
    //메세지 타입
    private MessageType type;
    //방번호
    private String roomId;
    //메세지 내용
    private String message;
    //누가 메세지를 쓰는건지(학번)
    private Long sender;
    //메세지 전송 시각
    private LocalDateTime time;
}
