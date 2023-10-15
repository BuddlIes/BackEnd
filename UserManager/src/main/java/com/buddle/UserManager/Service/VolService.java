package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.*;
import com.buddle.UserManager.Entity.VolunteerInfo;
import com.buddle.UserManager.Repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolService {
    @Autowired
    VolunteerRepository volRepository;

    public List<VolListDto> checkVolList(String hashtag) {
        List<VolunteerInfo> volInfosByHashTag = volRepository.findByhashTagOrderByWriteTime(hashtag);
        List<VolunteerInfo> volAllInfos = volRepository.findAllOrderByWriteTime(hashtag);

        if(hashtag=="전체")
        {
            List<VolListDto> allVolList = volAllInfos.stream().map(
                    m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getVolTime(), m.getPlace())
            ).collect(Collectors.toList());

            return allVolList;
        }
        else
        {
            List<VolListDto> volListByHashTag = volInfosByHashTag.stream().map(
                    m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getVolTime(), m.getPlace())
            ).collect(Collectors.toList());

            return volListByHashTag;
        }
    }

    public VolContentDto checkVolContent(Long volunteerId) {
        Optional<VolunteerInfo> volContentInfo = volRepository.findById(volunteerId);
        VolunteerInfo result = volContentInfo.get();

        VolContentDto volDto = new VolContentDto(
                result.getVolunteerId(),
                result.getWriter(),
                result.getHashtag(),
                result.getTitle(),
                result.getDetailed(),
                result.getImg(),

                result.getWriteTime(),
                result.getVolTime(),
                result.getPlace(),
                result.getWhoVol(),
                result.getCompleted(),

                result.getLikes(),
                result.getChatNum()
        );

        return volDto;

    }

    public String registerVol(VolUploadRequestDto vol) {
        volRepository.save(vol.toEntity());

        return "Register Completed";
    }
}
