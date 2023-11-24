package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.StampRequestDto;
import com.buddle.UserManager.entity.*;
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

    @Autowired
    NFTRepository nftRepository;

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

        //스탬프 정보와 스탬프 얻은 정보를 가져오기
        Optional<StampAcquireInfo> optStampAcquireInfo = stampAcquireRepository.findByUserNumberAndStampId(user_number, stamp_id);
        Optional<StampInfo> optStampInfo = stampRepository.findById(stamp_id);

        StampResponseDto stampResponseDto = new StampResponseDto();

        //스탬프가 존재하지 않는다면 그냥 리턴
        if(optStampInfo.isEmpty()){

            return stampResponseDto;

        }//획득 안했다면 스탬프 정보만 리턴
        else if (optStampAcquireInfo.isEmpty()) {

            stampResponseDto.setStamp_id(optStampInfo.get().getStamp_id());
            stampResponseDto.setStamp_name(optStampInfo.get().getStamp_name());
            stampResponseDto.setStamp_type(optStampInfo.get().getStamp_type());
            stampResponseDto.setLogin_number(optStampInfo.get().getLogin_number());
            stampResponseDto.setPost_number(optStampInfo.get().getPost_number());
            stampResponseDto.setComment_number(optStampInfo.get().getComment_number());
            stampResponseDto.setDo_volunteer_number(optStampInfo.get().getDo_volunteer_number());
            stampResponseDto.setReview_number(optStampInfo.get().getReview_number());
            return stampResponseDto;

        }//획득 했다면 얻은 정보까지 리턴
        else
        {
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
            return stampResponseDto;
        }

    }

    /*어떤 유저의 스탬프들의 획득 여부 정보를 모두 리턴함*/
    public List<StampResponseDto> getStampList(Long user_number) {


        List<StampInfo> allStamp = stampRepository.findAll();

        //StampDto로 만들어서 리턴
        List<StampResponseDto> stampResponseDtoList = new ArrayList<>();

        for (StampInfo one : allStamp) {

            StampResponseDto stampResponseDto = new StampResponseDto();
            stampResponseDto.setStamp_id(one.getStamp_id());
            stampResponseDto.setStamp_name(one.getStamp_name());
            stampResponseDto.setStamp_type(one.getStamp_type());
            stampResponseDto.setLogin_number(one.getLogin_number());
            stampResponseDto.setPost_number(one.getPost_number());
            stampResponseDto.setComment_number(one.getComment_number());
            stampResponseDto.setDo_volunteer_number(one.getDo_volunteer_number());
            stampResponseDto.setReview_number(one.getReview_number());

            Optional<StampAcquireInfo> stampAcquireInfo = stampAcquireRepository.findByUserNumberAndStampId(user_number, one.getStamp_id());
            if(stampAcquireInfo.isPresent()) {
                stampResponseDto.setAcquire_id(stampAcquireInfo.get().getAcquire_id());
                stampResponseDto.setAcquire_time(stampAcquireInfo.get().getAcquire_time());
            }
            stampResponseDtoList.add(stampResponseDto);
        }

        return stampResponseDtoList;

    }


    public Long getStampCount(Long user_number) {

        //return stampAcquireRepository.countDistinctByUserNumberEquals(user_number);
        //유저가 존재하는지 확인
        Optional<UserInfo> optUserInfo = userRepository.findById(user_number);
        if (optUserInfo.isEmpty()) {
            return 0L;
        }

        //이 유저가 쓸 수 있는 스탬프의 수 가져오기
        Long stamp_usable = 0L;
        stamp_usable = stampAcquireRepository.countDistinctByUserNumberEquals(user_number);
        List<Object[]> his_nfts = nftRepository.findNFTInfoListWithAcquire(user_number);
        for (Object[] one : his_nfts) {
            stamp_usable = stamp_usable - ((NFTInfo) one[0]).getNeeded_stamp();
        }

        return stamp_usable;
    }
}
