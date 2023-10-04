package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.StampDto;
import com.buddle.UserManager.Entity.StampAcquireInfo;
import com.buddle.UserManager.Entity.StampInfo;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.StampAcquireRepository;
import com.buddle.UserManager.Repository.StampRepository;
import com.buddle.UserManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    private void checkAcquireStamp(){

    }

    /*어떤 유저가 어떤 스탬프를 획득한 상태인지 확인하고 그 정보를 리턴함*/
    public StampDto getStamp(Long user_number, Long stamp_id) {

        //StampAcquireInfo를 조희해서 이 유저가 이 스탬프를 획득했는지 확인
        Optional<StampAcquireInfo> optStampAcquireInfo = stampAcquireRepository.findByUserNumberAndStampId(user_number, stamp_id);

        //획득 안했다면 안했음을 알림
        StampDto stampDto = new StampDto();
        if (optStampAcquireInfo.isEmpty()) {
            return stampDto;
        }

        //획득 했다면 StampInfo를 조회해서 가져옴
        Optional<StampInfo> optStampInfo = stampRepository.findById(optStampAcquireInfo.get().getStampId());

        //StampInfo와 StampAcquireInfo를 합쳐서 StampDto로 만들기
        stampDto.setStamp_id(optStampInfo.get().getStamp_id());
        stampDto.setStamp_name(optStampInfo.get().getStamp_name());
        stampDto.setStamp_type(optStampInfo.get().getStamp_type());
        stampDto.setLogin_number(optStampInfo.get().getLogin_number());
        stampDto.setPost_number(optStampInfo.get().getPost_number());
        stampDto.setComment_number(optStampInfo.get().getComment_number());
        stampDto.setDo_volunteer_number(optStampInfo.get().getDo_volunteer_number());
        stampDto.setHad_volunteer_number(optStampInfo.get().getHad_volunteer_number());
        stampDto.setReview_number(optStampInfo.get().getReview_number());

        stampDto.setAcquire_id(optStampAcquireInfo.get().getAcquire_id());
        stampDto.setAcquire_time(optStampAcquireInfo.get().getAcquire_time());

        //그걸 전달함
        return stampDto;


    }

    /*어떤 유저의 스탬프들의 획득 여부 정보를 모두 리턴함*/
//    public List<StampDto> getStampList(Long stampDto) {
//
//    }
}
