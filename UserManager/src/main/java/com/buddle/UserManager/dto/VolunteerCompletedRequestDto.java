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

    public VolunteerInfo toEntity() {
        VolunteerInfo volInfo = new VolunteerInfo();
        volInfo.setVolunteerId(this.volunteerId);
        volInfo.setWhoVol(this.whoVol);

        return volInfo;
    }
}
