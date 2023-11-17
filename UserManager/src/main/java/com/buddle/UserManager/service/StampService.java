package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.StampRequestDto;
import com.buddle.UserManager.entity.StampAcquireInfo;
import com.buddle.UserManager.entity.StampConstant;
import com.buddle.UserManager.entity.StampInfo;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.repository.*;

import com.buddle.UserManager.dto.StampResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StampService {
    
    @Autowired
    StampAcquireRepository stampAcquireRepository;

    @Autowired
    StampRepository stampRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VolunteerRepository volunteerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    CommentRepository commentRepository;

    /*이 스탬프의 획득 조건을 만족하는 지 확인함*/
    public Boolean checkAcquireStamp(StampRequestDto reqDto){

        //stamp_type = 0x|-접속형-|-게시글형-|-댓글형-|-봉사하기형-|-리뷰형-|-기타형-|

        //UserInfo 가져오기
        Optional<UserInfo> optUserInfo = userRepository.findById(reqDto.getUser_number());
        if(optUserInfo.isEmpty()){return false;}

        //StampInfo 가져오기
        Optional<StampInfo> optStampInfo = stampRepository.findById(reqDto.getStamp_id());
        if(optStampInfo.isEmpty()){return false;}

        //이미 유저가 스탬프를 가지고 있는지 확인
        Optional<StampAcquireInfo> optStampAcquireInfo = stampAcquireRepository.findByUserNumberAndStampId(reqDto.getUser_number(), reqDto.getStamp_id());
        if(optStampAcquireInfo.isPresent()) return false;

        Integer stamp_type = optStampInfo.get().getStamp_type();

        //post 수 세기
        Long post_number = volunteerRepository.countDistinctByWriterEquals(optUserInfo.get().getUser_number());

        //do volunteer 수 세기
        Long do_vol_number = volunteerRepository.countDistinctByWhoVolEqualsAndCompletedEquals(optUserInfo.get().getUser_number(), 0);

        //review 수 세기
        Long review_number = reviewRepository.countBySenderNumberEquals(optUserInfo.get().getUser_number());

        //comment 수 세기
        Long comment_number = commentRepository.countByWhoCommEquals(optUserInfo.get().getUser_number());

        //login 횟수 체크하기
        if( (stamp_type & StampConstant.STAMP_TYPE_LOGIN) > 0) {
            if ( (optUserInfo.get().getLogin_num().intValue()) < (optStampInfo.get().getLogin_number()) ) {
                return false;
            }
        }

        //post 횟수 체크하기
        if( (stamp_type & StampConstant.STAMP_TYPE_POST) > 0){
            if(post_number < (optStampInfo.get().getPost_number())){
                return false;
            }
        }

        //comment 횟수 체크하기
        if( (stamp_type & StampConstant.STAMP_TYPE_COMMENT) > 0){
            if(comment_number < (optStampInfo.get().getComment_number())){
                return false;
            }
        }

        //봉사 참여 수 체크하기
        if( (stamp_type & StampConstant.STAMP_TYPE_DO_VOL) > 0) {
            if ( do_vol_number < (optStampInfo.get().getDo_volunteer_number()) ) {
                return false;
            }
        }

        //review 횟수 체크하기
        if( (stamp_type & StampConstant.STAMP_TYPE_REVIEW) > 0) {
            if ( review_number < optStampInfo.get().getReview_number() ) {
                return false;
            }
        }

        return true;
    }

    /*스탬프 힉득 정보를 DB에 저장함*/
    public Boolean acquireStamp(StampRequestDto reqDto){

        //이 스탬프를 얻을 수 있는지 확인
        if(!checkAcquireStamp(reqDto)) return false;

       // StampAcquireInfo 만들기
        StampAcquireInfo stampAcquireInfo = new StampAcquireInfo();
        stampAcquireInfo.setStampId(reqDto.getStamp_id());
        stampAcquireInfo.setUserNumber(reqDto.getUser_number());
        stampAcquireInfo.setAcquire_time(LocalDateTime.now());

        //스탬프의 획득 정보 저장
        stampAcquireRepository.save(stampAcquireInfo);

        return true;
    }

    /*어떤 유저가 어떤 스탬프를 획득한 상태인지 확인하고 그 정보를 리턴함*/
    public StampResponseDto getStamp(Long user_number, Long stamp_id) {

        //StampAcquireInfo를 조희해서 이 유저가 이 스탬프를 획득했는지 확인
        Optional<StampAcquireInfo> optStampAcquireInfo = stampAcquireRepository.findByUserNumberAndStampId(user_number, stamp_id);

        //획득 안했다면 안했음을 알림
        StampResponseDto stampResponseDto = new StampResponseDto();
        if (optStampAcquireInfo.isEmpty()) {
            return stampResponseDto;
        }

        //획득 했다면 StampInfo를 조회해서 가져옴
        Optional<StampInfo> optStampInfo = stampRepository.findById(optStampAcquireInfo.get().getStampId());
        if(optStampInfo.isEmpty()){ return new StampResponseDto();}

        //StampInfo와 StampAcquireInfo를 합쳐서 StampDto로 만들기
        stampResponseDto.setStamp_id(optStampInfo.get().getStamp_id());
        stampResponseDto.setStamp_name(optStampInfo.get().getStamp_name());
        stampResponseDto.setStamp_type(optStampInfo.get().getStamp_type());
        stampResponseDto.setLogin_number(optStampInfo.get().getLogin_number());
        stampResponseDto.setPost_number(optStampInfo.get().getPost_number());
        stampResponseDto.setComment_number(optStampInfo.get().getComment_number());
        stampResponseDto.setDo_volunteer_number(optStampInfo.get().getDo_volunteer_number());
        stampResponseDto.setReview_number(optStampInfo.get().getReview_number());

        stampResponseDto.setAcquire_id(optStampAcquireInfo.get().getAcquire_id());
        stampResponseDto.setAcquire_time(optStampAcquireInfo.get().getAcquire_time());

        //그걸 전달함
        return stampResponseDto;


    }

    /*어떤 유저의 스탬프들의 획득 여부 정보를 모두 리턴함*/
    public List<StampResponseDto> getStampList(Long user_number) {

        //StampInfo를 조회하되, join으로 StampAcquireInfo를 사용하고, 조건은 user_number == a.user_number
        List<Object[]> all = stampRepository.findStampInfoListWithAcquire(user_number);

        //StampDto로 만들어서 리턴
        List<StampResponseDto> stampResponseDtoList = new ArrayList<>();

        for (Object[] one : all) {

            StampResponseDto stampResponseDto = new StampResponseDto();
            stampResponseDto.setStamp_id( ((StampInfo)one[0]).getStamp_id() );
            stampResponseDto.setStamp_name( ((StampInfo)one[0]).getStamp_name() );
            stampResponseDto.setStamp_type( ((StampInfo)one[0]).getStamp_type() );
            stampResponseDto.setLogin_number( ((StampInfo)one[0]).getLogin_number() );
            stampResponseDto.setPost_number( ((StampInfo)one[0]).getPost_number() );
            stampResponseDto.setComment_number( ((StampInfo)one[0]).getComment_number() );
            stampResponseDto.setDo_volunteer_number( ((StampInfo)one[0]).getDo_volunteer_number() );
            stampResponseDto.setReview_number( ((StampInfo)one[0]).getReview_number() );

            stampResponseDto.setAcquire_id( ((StampAcquireInfo)one[1]).getAcquire_id() );
            stampResponseDto.setAcquire_time( ((StampAcquireInfo)one[1]).getAcquire_time() );

            stampResponseDtoList.add(stampResponseDto);
        }

        return stampResponseDtoList;

    }


    public Long getStampCount(Long user_number){

        return stampAcquireRepository.countDistinctByUserNumberEquals(user_number);
    }
}
