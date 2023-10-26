package com.buddle.UserManager.Dto;

import com.buddle.UserManager.Entity.VolunteerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolUploadRequestDto {

    private Long volunteerId; //봉사 게시물 아이디

    private Long writer; //게시물 작성자
    private String title; //게시물 제목
    private String detailed; //글 내용
    private String hashtag; //해시태그
    private String img; /* 타입 변경될 수 있음 */

    private LocalDateTime writeTime; //게시물 등록일시
    private String whenVol; //봉사 요청일시
    private String place; //봉사 요청장소
    private Float volTime; //봉사 활동시간 (시간 단위)

    public VolunteerInfo toEntity()
    {
        VolunteerInfo volInfo = new VolunteerInfo();

        volInfo.setVolunteerId(this.volunteerId);

        volInfo.setWriter(this.writer);
        volInfo.setTitle(this.title);
        volInfo.setDetailed(this.detailed);
        volInfo.setHashtag(this.hashtag);
        volInfo.setImg(this.img);

        volInfo.setWriteTime(this.writeTime);
        volInfo.setWhenVol(this.whenVol);
        volInfo.setPlace(this.place);
        volInfo.setVolTime(this.volTime);

        return volInfo;
    }
}
