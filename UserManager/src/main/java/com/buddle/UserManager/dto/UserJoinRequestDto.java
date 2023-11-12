package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserJoinRequestDto {

    private Long user_number;

    private String name;
    private String email;
    private String wallet;
    private String password;
    private String nickname;

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

        userInfo.setName(this.name);
        userInfo.setEmail(this.email);
        userInfo.setWallet(this.wallet);
        userInfo.setPassword(this.password);
        userInfo.setNickname(this.nickname);
        userInfo.setCreated_at(this.created_at);

        return userInfo;
    }

}
