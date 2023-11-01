package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.VolCommentInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolCommentDto {
    private Long id; //고유 아이디

    private Long volunteerId; //게시물 아이디
    private String comments; //댓글 내용
    private Long whoComm; //댓글 작성자
    private LocalDateTime time; //댓글 작성 일시

    public VolCommentInfo toEntity()
    {
        VolCommentInfo commentInfo = new VolCommentInfo();

        commentInfo.setId(this.id);
        commentInfo.setVolunteerId(this.volunteerId);
        commentInfo.setComments(this.comments);
        commentInfo.setWhoComm(this.whoComm);
        commentInfo.setTime(this.time);

        return commentInfo;
    }
}
