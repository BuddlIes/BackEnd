package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.VolunteerInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VolunteerApplyRequestDto {
    private Long volunteerId; //봉사 게시물 아이디
    private Long whoVol; //봉사 신청자(학번)

    public VolunteerInfo toEntity() {
        VolunteerInfo volInfo = new VolunteerInfo();
        volInfo.setVolunteerId(this.volunteerId);
        volInfo.setWhoVol(this.whoVol);

        return volInfo;
    }
}
