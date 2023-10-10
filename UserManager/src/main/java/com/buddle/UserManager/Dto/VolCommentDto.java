package com.buddle.UserManager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolCommentDto {
    private Long id;

    private Long volunteerId; //게시물 아이디
    private String comments; //댓글 내용
    private Long whoComm; //댓글 작성자
    private Long time; //댓글 작성 일시
}
