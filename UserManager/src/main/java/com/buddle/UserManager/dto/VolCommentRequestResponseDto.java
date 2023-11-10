package com.buddle.UserManager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class VolCommentRequestResponseDto {
    private Long id; //고유 아이디
    private Long whoComm; //댓글 작성자
    private LocalDateTime time; //댓글 작성 일시
}
