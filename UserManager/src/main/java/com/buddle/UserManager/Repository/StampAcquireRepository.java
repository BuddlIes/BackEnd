package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.StampAcquireInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface StampAcquireRepository extends JpaRepository<StampAcquireInfo, Long> {

    //주의! attribute 명에 언더바가 있으면 findBy에서 인식을 못한당
    Optional<StampAcquireInfo> findByUserNumberAndStampId(Long user_number, Long stamp_id);
    List<StampAcquireInfo> findAll();

    Long countDistinctByUserNumberEquals(Long user_number);
}
