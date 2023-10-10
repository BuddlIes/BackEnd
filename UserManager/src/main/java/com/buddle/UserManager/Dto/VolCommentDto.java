package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.MessageInfo;
import com.buddle.UserManager.Entity.VolCommentInfo;
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
