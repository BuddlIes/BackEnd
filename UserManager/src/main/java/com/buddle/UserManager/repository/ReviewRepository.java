package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.ReviewInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewInfo, Long>{

    public Optional<ReviewInfo> findByReviewId(Long review_id);

    public List<ReviewInfo> findAllByReceiverNumberEqualsOrSenderNumberEquals(Long user_number1, Long user_number2);
    public List<ReviewInfo> findAllByReceiverNumber(Long receiver_number);
    public List<ReviewInfo> findAllBySenderNumber(Long sender_number);

    public Long countBySenderNumberEquals(Long sender_number);
}
