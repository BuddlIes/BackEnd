package com.buddle.UserManager.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NFTRequestDto {
    private Long user_number;
    private Long nft_id;
    private String wallet;
}
