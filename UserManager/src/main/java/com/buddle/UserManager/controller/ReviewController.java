package com.buddle.UserManager.controller;

import com.buddle.UserManager.dto.ReviewRequestDto;
import com.buddle.UserManager.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review/apply")
    public String reviewApply(@RequestBody ReviewRequestDto reqDto)
    {
        return reviewService.apply(reqDto);
    }
}





