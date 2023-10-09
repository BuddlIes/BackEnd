package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.VolunteerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolListDto {

    private Long volunteerId; //봉사 게시물 아이디

    private Long writer; //게시물 작성자
    private String hashtag; //해시태그
    private String title;
    private String img; /* 타입 변경될 수 있음 */

    private Long writeTime; //게시물 등록일시
    private Long volTime; //봉사 요청일시
    private String place;

}
