package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserLoginRequestDto {
    private String ajou_email;
    private String password;
    private LocalDateTime recent_access;

    public UserInfo toEntity() {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(this.ajou_email);
        userInfo.setPassword(this.password);
        userInfo.setRecent_access(this.recent_access);
        return userInfo;
    }
}
