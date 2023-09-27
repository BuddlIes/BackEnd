package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserJoinRequestDto {

    private Long user_number;

    private String user_name;
    private String ajou_email;
    private String wallet_address;
    private String password;
    private String user_nickname;
    private Long created_at;

    public UserInfo toEntity() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_number(this.user_number);
        userInfo.setName(this.user_name);
        userInfo.setEmail(this.ajou_email);
        userInfo.setWallet(this.wallet_address);
        userInfo.setPassword(this.password);
        userInfo.setNickname(this.user_nickname);
        userInfo.setCreated_data(this.created_at);
        return userInfo;
    }

}
