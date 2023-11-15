package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.ReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewInfo, Long>{

    public Optional<ReviewInfo> findByReviewId(Long review_id);
    public Optional<ReviewInfo> findByReceiverNumber(Long receiver_number);
    public Optional<ReviewInfo> findBySenderNumber(Long sender_number);
    public Long countBySenderNumberEquals(Long sender_number);
}
