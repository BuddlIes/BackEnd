package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MsgDto {
    private Long id;
    private Long chatroomid;
    private LocalDateTime createdat;
    private String messagecontent;
    private Long messagefrom;
}
