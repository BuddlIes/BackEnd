package com.buddle.UserManager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StampRequestDto {

    private Long user_number;
    private Long stamp_id;
}
