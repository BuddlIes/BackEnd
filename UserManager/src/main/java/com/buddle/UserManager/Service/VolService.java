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
        List<VolunteerInfo> volInfosByHashTag = volRepository.findByHashTagOrderByWriteTime(hashtag);
        List<VolunteerInfo> volAllInfos = volRepository.findAllOrderByWriteTime(hashtag);

        if(hashtag=="전체")
        {
            List<VolListDto> allVolList = volAllInfos.stream().map(
                    m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getWhenVol(), m.getPlace(), m.getVolTime())
            ).collect(Collectors.toList());

            return allVolList;
        }
        else
        {
            List<VolListDto> volListByHashTag = volInfosByHashTag.stream().map(
                    m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getWhenVol(), m.getPlace(), m.getVolTime())
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
                result.getWhenVol(),
                result.getPlace(),
                result.getWhoVol(),
                result.getVolTime(),
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

    public List<VolListDto> checkMyCompletedVolList(Long whoVol) {
        List<VolunteerInfo> myVolList = volRepository.findByWhoVolOrderByWriteTime(whoVol);

        List<VolListDto> myVolDtoList = myVolList.stream().map(
                m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getWhenVol(), m.getPlace(), m.getVolTime())
        ).collect(Collectors.toList());

        return myVolDtoList;
    }
}