package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.ReviewRequestDto;
import com.buddle.UserManager.Dto.UserJoinRequestDto;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    UserRepository userRepository;

    public ReviewService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*리뷰를 상대에게 적용하기*/
    public String apply(ReviewRequestDto review) {

        Optional<UserInfo> optRecieverInfo = userRepository.findById(review.getReciever_number());

        //상대방이 존재하지 않는다면 에러 report
        if (optRecieverInfo.isEmpty()) {
            return "Reciever is not founded";
        }

        //리뷰 점수 적용
        switch (review.getMeasure()) {
            case BAD:
                optRecieverInfo.get().setTemperature( optRecieverInfo.get().getTemperature() - 0.1 );
                break;
            case NORMAL:
                optRecieverInfo.get().setTemperature( optRecieverInfo.get().getTemperature());
                break;
            case GOOD:
                optRecieverInfo.get().setTemperature( optRecieverInfo.get().getTemperature() + 0.1 );
                break;
            case EXELLENT:
                optRecieverInfo.get().setTemperature( optRecieverInfo.get().getTemperature() + 0.2 );
                break;
            default:
                break;
        }

        return "Review has Successfully Aplied";
    }
}
