package com.buddle.UserManager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolListDto {

    private Long volunteerId; //봉사 게시물 아이디

    private Long writer; //게시물 작성자
    private String hashtag; //해시태그
    private String title; //게시물 제목
    private String img; /* 타입 변경될 수 있음 */

    private Long writeTime; //게시물 등록일시
    private Long whenVol; //봉사 요청일시
    private String place; //봉사 요청장소
    private Float volTime; //봉사 활동시간 (시간 단위)

}