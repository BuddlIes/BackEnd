package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class LoginRequestResponseDto {
    private Long userNum;
    private String userName;
    private String email;
    private String nickname;
    private String token;
}
