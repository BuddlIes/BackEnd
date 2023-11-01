package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StampDto {

    //StampInfo에서 온 필드
    private Long stamp_id;
    private String stamp_name;
    private Integer stamp_type;
    private Integer login_number;
    private Integer post_number;
    private Integer comment_number;
    private Integer do_volunteer_number;
    private Integer had_volunteer_number;
    private Integer review_number;

    //StampAcquireInfo에서 온 필드
    private Long acquire_id;
    private LocalDateTime acquire_time;
}
