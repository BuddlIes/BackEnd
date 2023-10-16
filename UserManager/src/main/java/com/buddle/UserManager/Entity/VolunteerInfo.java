package com.buddle.UserManager.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "volList_info")
public class VolunteerInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long volunteerId; //봉사 게시물 아이디

    @Column private Long writer; //게시물 작성자(피봉사자)
    @Column private String hashtag; //해시태그
    @Column private String title; //게시물 제목
    @Column private String detailed; //글 내용
    @Column private String img; /* 타입 변경될 수 있음 */

    @Column private Long writeTime; //게시물 등록일시
    @Column private Long whenVol; //봉사 요청일시
    @Column private String place; //봉사 요청장소
    @Column private Long whoVol; //봉사자
    @Column private Float volTime; //봉사 활동시간 (시간 단위)
    @Column private Integer completed; //완료 0, 미완료 1

    @Column private Long likes; //관심 등록 수

    @Column private Long chatNum; //받은 채팅 수
}
