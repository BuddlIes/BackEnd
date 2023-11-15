package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChatMessage {
    private Integer roomSeq;
    private String message;
    //누가 메세지를 쓰는건지.(학번)
}
