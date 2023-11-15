package com.buddle.UserManager.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review_info")
public class ReviewInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long reviewId;

    @Column private ReviewMeasure measure; //enum 타입, 어떤 평가를 했는지의 정도
    @Column private Long senderNumber; //리뷰 쓴 사람의 사번, 학번
    @Column private Long receiverNumber; //리뷰 받은 사람의 사번, 학번
    @Column private LocalDateTime writeTime; //리뷰 쓴 시간
}
