package com.buddle.UserManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vol_comment_info")
public class VolCommentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column private Long volunteerId; //게시물 아이디
    @Lob private String comments; //댓글 내용
    @Column private Long whoComm; //댓글 작성자
    @Column private LocalDateTime time; //댓글 작성 일시
}
