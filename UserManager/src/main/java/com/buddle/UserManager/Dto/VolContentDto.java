package com.buddle.UserManager.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolContentDto {

    private Long volunteerId; //봉사 게시물 아이디

    private Long writer; //게시물 작성자
    private String hashtag; //해시태그
    private String title;
    private String detailed;
    private String img; /* 타입 변경될 수 있음 */

    private Long writeTime; //게시물 등록일시
    private Long volTime; //봉사 요청일시
    private String place;
    private Long whoVol; //봉사자
    private Long completed; //완료 0, 미완료 1

    private Long likes; //관심 등록 수

    private Long chatNum; //받은 채팅 수

//    public VolunteerInfo toEntity() {
//        VolunteerInfo volInfo = new VolunteerInfo();
//        volInfo.setVolunteerId(this.volunteerId);
//        volInfo.setWriter(this.writer);
//        volInfo.setHashtag(this.hashtag);
//        volInfo.setTitle(this.title);
//        volInfo.setDetailed(this.detailed);
//        volInfo.setImg(this.img);
//        volInfo.setWriteTime(this.writeTime);
//        volInfo.setVolTime(this.volTime);
//        volInfo.setPlace(this.place);
//        volInfo.setWhoVol(this.whoVol);
//        volInfo.setCompleted(this.completed);
//        volInfo.setLikes(this.likes);
//        volInfo.setChatNum(this.chatNum);
//
//        return volInfo;
//    }
}
