package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserLoginRequestDto {

//    private Long user_number;
//    private String user_name;
    private String ajou_email;
    private String password;
//    private String user_nickname;
    private Long recent_access;

    public UserInfo toEntity() {
        UserInfo userInfo = new UserInfo();
//        userInfo.setUser_number(this.user_number);
//        userInfo.setName(this.user_name);
        userInfo.setEmail(this.ajou_email);
        userInfo.setPassword(this.password);
//        userInfo.setNickname(this.user_nickname);
        userInfo.setRecent_access(this.recent_access);
        return userInfo;
    }
}
