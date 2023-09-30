package com.buddle.UserManager.Service;

import com.buddle.UserManager.Dto.ReviewRequestDto;
import com.buddle.UserManager.Entity.UserInfo;
import com.buddle.UserManager.Repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReviewServiceTest {

    ReviewService reviewService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    private void beforeEach(){
        userRepository.deleteAll();
        reviewService = new ReviewService(userRepository);
    }

    @Test
    void apply() {

//        //given
//        UserInfo user1 = new UserInfo();
//        user1.setUser_number(202020810L);
//        user1.setTemperature(36.5);
//
//        UserInfo user2 = new UserInfo();
//        user2.setUser_number(201924503L);
//        user2.setTemperature(36.5);
//
//        ReviewRequestDto.Measure measure = ReviewRequestDto.Measure.GOOD;
//        ReviewRequestDto reviewDto = new ReviewRequestDto(measure, user1.getUser_number(), user2.getUser_number());
//
//        //when
//        reviewService.apply(reviewDto);
//
//        //then
//        Assertions.assertEquals(36.6, user2.getTemperature());

    }


}
