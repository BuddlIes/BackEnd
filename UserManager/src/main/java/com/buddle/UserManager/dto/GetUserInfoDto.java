package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GetUserInfoDto {

    private Long userNum;
    private String userName;
    private String email;
    private String nickname;
}
