package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.ReviewMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequestDto {


    private ReviewMeasure measure;
    private Long sender_number;
    private Long reciever_number;


}
