package com.buddle.UserManager.Controller;

import com.buddle.UserManager.Dto.ReviewRequestDto;
import com.buddle.UserManager.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review")
    public String reviewApply(@RequestBody ReviewRequestDto reqDto)
    {
        return reviewService.apply(reqDto);
    }
}
