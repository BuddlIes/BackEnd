package com.buddle.UserManager.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NFTResponseDto {

    //NFTInfo에서 온 필드
    private Long nft_id;
    private String nft_name;
    private Integer nft_category;
    private Integer discount_rate;
    private Integer needed_stamp;

    //NFTAcquireInfo에서 온 필드
    private Long acquire_id;
    private LocalDateTime acquire_time;
}
