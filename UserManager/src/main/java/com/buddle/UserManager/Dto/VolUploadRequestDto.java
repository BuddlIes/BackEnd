package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolUploadRequestDto {
    private Long user_number;

    private String user_name;
    private String ajou_email;
    private String login_id;
    private String password;
    private String user_nickname;

    public UserInfo toEntity() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_number(this.user_number);
        userInfo.setName(this.user_name);
        userInfo.setAjou_email(this.ajou_email);
        userInfo.setPassword(this.password);
        userInfo.setUser_nickname(this.user_nickname);
        return userInfo;
    }
}
