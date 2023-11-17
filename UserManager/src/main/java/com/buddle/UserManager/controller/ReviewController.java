package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.ReviewRequestDto;
import com.buddle.UserManager.dto.ReviewResponseDto;
import com.buddle.UserManager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review/apply")
    public String reviewApply(@RequestBody ReviewRequestDto reqDto){

        return reviewService.apply(reqDto);
    }

    @GetMapping("/review/get_one_review")
    public ReviewResponseDto getOneReview(@RequestParam Long review_id){return reviewService.getOneReview(review_id);}

    @GetMapping("/review/get_review_list_all")
    public List<ReviewResponseDto> getReviewListAll(@RequestParam Long user_number){return reviewService.getReviewListAll(user_number);}

    @GetMapping("/review/get_review_list_send")
    public List<ReviewResponseDto> getReviewListSend(@RequestParam Long user_number){return reviewService.getReviewListSend(user_number);}

    @GetMapping("/review/get_review_list_receive")
    public List<ReviewResponseDto> getReviewListReceive(@RequestParam Long user_number){return reviewService.getReviewListReceive(user_number);}

}





