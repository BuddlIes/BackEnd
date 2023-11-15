package com.buddle.UserManager.service;

import com.buddle.UserManager.dto.ReviewRequestDto;
import com.buddle.UserManager.dto.ReviewResponseDto;
import com.buddle.UserManager.dto.StampResponseDto;
import com.buddle.UserManager.entity.ReviewInfo;
import com.buddle.UserManager.entity.StampAcquireInfo;
import com.buddle.UserManager.entity.StampInfo;
import com.buddle.UserManager.entity.UserInfo;
import com.buddle.UserManager.repository.ReviewRepository;
import com.buddle.UserManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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

        Optional<UserInfo> optReceiverInfo = userRepository.findById(review.getReceiver_number());
        Optional<UserInfo> optSenderInfo = userRepository.findById(review.getSender_number());

        //상대방이 존재하지 않는다면 에러 report
        if (optReceiverInfo.isEmpty() || optSenderInfo.isEmpty()) {
            return "Reciever or Sender is not founded";
        }

        //리뷰 점수 적용
        switch (review.getMeasure()) {
            case BAD:
                optReceiverInfo.get().setTemperature( optReceiverInfo.get().getTemperature() - 0.1 );
                break;
            case NORMAL:
                optReceiverInfo.get().setTemperature( optReceiverInfo.get().getTemperature());
                break;
            case GOOD:
                optReceiverInfo.get().setTemperature( optReceiverInfo.get().getTemperature() + 0.1 );
                break;
            case EXELLENT:
                optReceiverInfo.get().setTemperature( optReceiverInfo.get().getTemperature() + 0.2 );
                break;
            default:
                break;
        }

        //변경사항을 적용
        userRepository.save(optReceiverInfo.get());

        //reviewInfo repository에 리뷰 추가
        ReviewInfo reviewInfo = new ReviewInfo();
        reviewInfo.setMeasure(review.getMeasure());
        reviewInfo.setReceiverNumber(review.getReceiver_number());
        reviewInfo.setSenderNumber(review.getSender_number());
        reviewInfo.setWriteTime(LocalDateTime.now());

        reviewRepository.save(reviewInfo);

        return "Review has Successfully Aplied";
    }

    public ReviewResponseDto getOneReview(Long review_id) {

        ReviewResponseDto reviewResponseDto = new ReviewResponseDto();

        Optional<ReviewInfo> review = reviewRepository.findByReviewId(review_id);

        //존재하지 않으면 빈 dto 리턴
        if(review.isEmpty()) return reviewResponseDto;

        reviewResponseDto.setReviewId(review.get().getReviewId());
        reviewResponseDto.setMeasure(review.get().getMeasure());
        reviewResponseDto.setReceiverNumber(review.get().getReceiverNumber());
        reviewResponseDto.setSenderNumber(review.get().getSenderNumber());
        reviewResponseDto.setWriteTime(review.get().getWriteTime());

        return reviewResponseDto;
    }

    public List<ReviewResponseDto> getReviewListAll(Long user_number) {

        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        List<ReviewInfo> reviewInfos = reviewRepository.findAllByReceiverNumberEqualsOrSenderNumberEquals(user_number, user_number);

        for (ReviewInfo one : reviewInfos) {

            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setReviewId( one.getReviewId() );
            reviewResponseDto.setMeasure(one.getMeasure());
            reviewResponseDto.setReceiverNumber(one.getReceiverNumber());
            reviewResponseDto.setSenderNumber(one.getSenderNumber());
            reviewResponseDto.setWriteTime(one.getWriteTime());

            reviewResponseDtos.add(reviewResponseDto);
        }

        return reviewResponseDtos;
    }

    public List<ReviewResponseDto> getReviewListSend(Long user_number) {

        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        List<ReviewInfo> reviewInfos = reviewRepository.findAllBySenderNumber(user_number);

        for (ReviewInfo one : reviewInfos) {

            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setReviewId( one.getReviewId() );
            reviewResponseDto.setMeasure(one.getMeasure());
            reviewResponseDto.setReceiverNumber(one.getReceiverNumber());
            reviewResponseDto.setSenderNumber(one.getSenderNumber());
            reviewResponseDto.setWriteTime(one.getWriteTime());

            reviewResponseDtos.add(reviewResponseDto);
        }

        return reviewResponseDtos;

    }

    public List<ReviewResponseDto> getReviewListReceive(Long user_number) {

        List<ReviewResponseDto> reviewResponseDtos = new ArrayList<>();

        List<ReviewInfo> reviewInfos = reviewRepository.findAllByReceiverNumber(user_number);

        for (ReviewInfo one : reviewInfos) {

            ReviewResponseDto reviewResponseDto = new ReviewResponseDto();
            reviewResponseDto.setReviewId( one.getReviewId() );
            reviewResponseDto.setMeasure(one.getMeasure());
            reviewResponseDto.setReceiverNumber(one.getReceiverNumber());
            reviewResponseDto.setSenderNumber(one.getSenderNumber());
            reviewResponseDto.setWriteTime(one.getWriteTime());

            reviewResponseDtos.add(reviewResponseDto);
        }

        return reviewResponseDtos;

    }
}
