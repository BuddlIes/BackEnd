package com.buddle.UserManager.Dto;

import lombok.Getter;
import lombok.Setter;

;

@Getter
@Setter
public class ReviewRequestDto {

    public enum Measure {BAD, NORMAL, GOOD, EXELLENT}
    private Measure measure;
   // private Integer measure;
    private Long sender_number;
    private Long reciever_number;

//    public ReviewRequestDto(Measure measure, Long sender_number, Long reciever_number){
//
//        this.measure = measure;
//        this.sender_number = sender_number;
//        this.reciever_number = reciever_number;
//    }


}
