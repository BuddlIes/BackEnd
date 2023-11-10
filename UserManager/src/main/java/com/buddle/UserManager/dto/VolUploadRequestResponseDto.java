package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class VolUploadRequestResponseDto {
    private Long volunteerId; //봉사 게시물 아이디
    private Long writer; //게시물 작성자
    private String title; //게시물 제목
    private String hashtag; //해시태그
    private LocalDateTime writeTime; //게시물 등록일시
}
