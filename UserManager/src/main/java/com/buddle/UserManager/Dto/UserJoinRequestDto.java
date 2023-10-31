package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserJoinRequestDto {

    private Long user_number;

    private String user_name;
    private String ajou_email;
    private String wallet_address;
    private String password;
    private String user_nickname;

    private Integer delete_yn;
    private LocalDateTime created_at;
    private Float vol_hour;
    private Long vol_num;

    private Double temperature;
    private Long compliment_num;
    private Integer alarm;

    public UserInfo toEntity() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUser_number(this.user_number);

        userInfo.setName(this.user_name);
        userInfo.setEmail(this.ajou_email);
        userInfo.setWallet(this.wallet_address);
        userInfo.setPassword(this.password);
        userInfo.setNickname(this.user_nickname);
        userInfo.setCreated_at(this.created_at);

        return userInfo;
    }

}
