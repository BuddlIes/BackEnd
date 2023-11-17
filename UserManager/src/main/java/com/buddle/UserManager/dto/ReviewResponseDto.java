package com.buddle.UserManager.dto;

import com.buddle.UserManager.entity.ReviewMeasure;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {

    private Long reviewId;
    private ReviewMeasure measure; //enum 타입, 어떤 평가를 했는지의 정도
    private Long senderNumber; //리뷰 쓴 사람의 사번, 학번
    private Long receiverNumber; //리뷰 받은 사람의 사번, 학번
    private LocalDateTime writeTime; //리뷰 쓴 시간
}
