package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.entity.VolunteerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Lob;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerCompletedRequestDto {

    private Long volunteerId; //봉사 게시물 아이디
    private Long whoVol; //봉사자(학번)
    private Float volTime; //봉사 활동시간 (시간 단위)
//    private Integer completed; //완료 0, 미완료 1
//    private LocalDateTime whencompleted; //봉사 완료 시각
//    private Long chatNum; //받은 채팅 수

    public VolunteerInfo toEntity() {
        VolunteerInfo volInfo = new VolunteerInfo();
        volInfo.setVolunteerId(this.volunteerId);
        volInfo.setWhoVol(this.whoVol);
        volInfo.setVolTime(this.volTime);
//        volInfo.setCompleted(this.completed);
//        volInfo.setWhencompleted(this.whencompleted);
//        volInfo.setChatNum(this.chatNum);

        return volInfo;
    }
}
