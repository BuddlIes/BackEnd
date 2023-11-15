package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.ReviewRequestDto;
import com.buddle.UserManager.entity.ReviewInfo;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.repository.ReviewRepository;
import com.buddle.UserManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.buddle.UserManager.entity.ReviewMeasure.*;

@Service
public class ReviewService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ReviewRepository reviewRepository;

    public ReviewService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /*리뷰를 상대에게 적용하기*/
    public String apply(ReviewRequestDto review) {

        Optional<UserInfo> optRecieverInfo = userRepository.findById(review.getReciever_number());
        Optional<UserInfo> optSenderInfo = userRepository.findById(review.getSender_number());

        //상대방이 존재하지 않는다면 에러 report
        if (optRecieverInfo.isEmpty() || optSenderInfo.isEmpty()) {
            return "Reciever or Sender is not founded";
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

        //변경사항을 적용
        userRepository.save(optRecieverInfo.get());

        //reviewInfo repository에 리뷰 추가
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setMeasure(review.getMeasure());
        reviewInfo.setReceiverNumber(review.getReciever_number());
        reviewInfo.setSenderNumber(review.getSender_number());
        reviewInfo.setWriteTime(LocalDateTime.now());

        reviewRepository.save(reviewInfo);

        return "Review has Successfully Aplied";
    }
}
