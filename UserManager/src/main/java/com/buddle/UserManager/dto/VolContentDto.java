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
public class VolContentDto {

    private Long volunteerId; //봉사 게시물 아이디

    private Long writer; //게시물 작성자
    private String hashtag; //해시태그
    private String title; //게시물 제목
    private String detailed; //글 내용
    private String img; /* 타입 변경될 수 있음 */

    private LocalDateTime writeTime; //게시물 등록일시
    private String whenVol; //봉사 요청일시
    private String place; //봉사 요청장소
    private Long whoVol; //봉사자
    private Float volTime; //봉사 활동시간 (시간 단위)
    private Integer completed; //완료 0, 미완료 1

    private Long likes; //관심 등록 수

    private Long chatNum; //받은 채팅 수

}
