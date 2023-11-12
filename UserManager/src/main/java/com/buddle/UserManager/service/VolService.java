package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.*;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.entity.VolCommentInfo;
import com.buddle.UserManager.entity.VolunteerInfo;
import com.buddle.UserManager.repository.CommentRepository;
import com.buddle.UserManager.repository.UserRepository;
import com.buddle.UserManager.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VolService {
    @Autowired
    VolunteerRepository volRepository;

    @Autowired
    CommentRepository commRepository;

    @Autowired
    UserRepository userRepository;

    public List<VolListDto> checkVolList(String hashtag) {

        if(hashtag.equals("전체"))
        {
            List<VolunteerInfo> volAllInfos = volRepository.findAllByOrderByVolunteerId();
            List<VolListDto> allVolList = volAllInfos.stream().map(
                    m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getWhenVol(), m.getPlace(), m.getVolTime())
            ).collect(Collectors.toList());

            return allVolList;
        }
        else
        {
            List<VolunteerInfo> volInfosByHashTag = volRepository.findByHashTagOrderByWriteTime(hashtag);
            List<VolListDto> volListByHashTag = volInfosByHashTag.stream().map(
                    m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getWhenVol(), m.getPlace(), m.getVolTime())
            ).collect(Collectors.toList());

            return volListByHashTag;
        }
    }

    public VolContentDto checkVolContent(Long volunteerId) {
        Optional<VolunteerInfo> volContentInfo = volRepository.findById(volunteerId);
        List<VolCommentInfo> volCommentInfo = commRepository.findComments(volunteerId);
        VolunteerInfo contentInfo = volContentInfo.get();

        VolContentDto volDto = new VolContentDto(
                contentInfo.getVolunteerId(),
                contentInfo.getWriter(),
                contentInfo.getHashtag(),
                contentInfo.getTitle(),
                contentInfo.getDetailed(),
                contentInfo.getImg(),

                contentInfo.getWriteTime(),
                contentInfo.getWhenVol(),
                contentInfo.getPlace(),
                contentInfo.getWhoVol(),
                contentInfo.getVolTime(),
                contentInfo.getCompleted(),
                contentInfo.getWhencompleted(),

                contentInfo.getLikes(),
                contentInfo.getChatNum(),

                null
        );

        List<VolCommentListDto> comments = new ArrayList<>();
        for(int i = 0; i< volCommentInfo.size(); i++) {
            VolCommentInfo thisComment = volCommentInfo.get(i);
            comments.add(new VolCommentListDto(thisComment.getId(), thisComment.getComments(),thisComment.getWhoComm(),thisComment.getTime()));
        }

        volDto.setCommentInfo(comments);

        return volDto;

    }

    public ResponseDataDto<VolUploadRequestResponseDto> registerVol(VolUploadRequestDto vol) {

        VolunteerInfo volunteerInfo = vol.toEntity();
        volunteerInfo.setChatNum(0L);
        volunteerInfo.setCompleted(1); //완료 0, 미완료 1
        volunteerInfo.setLikes(0L);
        volunteerInfo.setWhoVol(0L); // 0이면 아직 봉사자 안정해짐
        volunteerInfo.setWriteTime(LocalDateTime.now()); //게시물 업로드 시간

        volRepository.save(volunteerInfo);

        return new ResponseDataDto("Register Success", 200, new VolUploadRequestResponseDto(volunteerInfo.getVolunteerId(), volunteerInfo.getWriter(), volunteerInfo.getTitle(), volunteerInfo.getHashtag(), volunteerInfo.getWriteTime()));
    }

    public List<VolListDto> checkMyCompletedVolList(Long whoVol) {
        List<VolunteerInfo> myVolList = volRepository.findByWhoVolOrderByWhencompleted(whoVol);

        List<VolListDto> myVolDtoList = myVolList.stream().map(
                m-> new VolListDto(m.getVolunteerId(),m.getWriter(),m.getHashtag(),m.getTitle(),m.getImg(),m.getWriteTime(), m.getWhenVol(), m.getPlace(), m.getVolTime())
        ).collect(Collectors.toList());

        return myVolDtoList;
    }

    public Float checkMyVolTime(Long user) {
        //사용자가 완료한 봉사 활동 시간 다 더하기
        Float totalVolunteerTime = volRepository.findBySumDurationByWhoVol(user);
        if (totalVolunteerTime != null) {
            return totalVolunteerTime;
        } else {
            return 0F;
        }
    }

    public Integer checkMyVolNum(Long user) {
        // 특정 사용자가 완료한 봉사 개수
        Integer completedVolunteerActivities = volRepository.findByCountCompletedVolunteerActivities(user);
        if (completedVolunteerActivities != null) {
            return completedVolunteerActivities;
        } else {
            return 0;
        }
    }

    public String uploadVolInfoWhenCompleted(VolunteerCompletedRequestDto completedvolDto) {
        Optional<VolunteerInfo> optVolunteerId = volRepository.findById(completedvolDto.getVolunteerId());
        Optional<UserInfo> optUserId = userRepository.findById(completedvolDto.getWhoVol());

        //완료 request(volunteerId, whoVol)를 받으면

        //VolunteerInfo 데베에서 volunteerId 찾은 다음,
        //해당 completed Column의 값을 0으로 바꾸고,
        //whencompleted 내역을 timestamp로 찍어서 저장한다.

        //whoVol과 UserInfo 데베의 user_number가 일치하는 것을 찾아서
        //해당 user의 vol_hour와 vol_num을 volTime과 1만큼 증가시킨다.


        if(optVolunteerId.isPresent() && optUserId.isPresent()) {
            VolunteerInfo volunteerInfo = optVolunteerId.get();
            UserInfo userInfo = optUserId.get();

            if(volunteerInfo.getCompleted()==0)
            {
                return "이미 완료된 게시물 입니다.";
            }
            else {
                volunteerInfo.setCompleted(0);
                volunteerInfo.setWhencompleted(LocalDateTime.now());
                volunteerInfo.setWhoVol(completedvolDto.getWhoVol());
                volRepository.save(volunteerInfo);

                userInfo.setVol_hour(userInfo.getVol_hour() + completedvolDto.getVolTime());
                userInfo.setVol_num(userInfo.getVol_num()+1);
                userRepository.save(userInfo);

                return "데이터가 정상적으로 업데이트되었습니다.";
            }
        }
        else if(optVolunteerId.isEmpty() && optUserId.isPresent()) {
            return "봉사 게시물을 찾을 수 없습니다.";
        }
        else if(optVolunteerId.isPresent() && optUserId.isEmpty()) {
            return "사용자 정보를 찾을 수 없습니다.";
        }
        else {
            return "사용자 정보와 봉사 게시물 정보 모두 찾을 수 없습니다. ";
        }
    }
}
