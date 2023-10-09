package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.ChatRoomDto;
import com.buddle.UserManager.Dto.MsgDto;
import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Dto.VolListDto;
import com.buddle.UserManager.Entity.MessageInfo;
import com.buddle.UserManager.Entity.VolunteerInfo;
import com.buddle.UserManager.Repository.VolListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolService {
    @Autowired
    VolListRepository volListRepository;

    public List<VolListDto> checkVolList(String hashtag) {
        List<VolunteerInfo> volInfosByHashTag = volListRepository.findByhashTagOrderByWriteTime(hashtag);
        List<VolunteerInfo> volAllInfos = volListRepository.findTopByVolunteerIdOrderByWriteTime(hashtag);

        if(hashtag=="all")
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
}
