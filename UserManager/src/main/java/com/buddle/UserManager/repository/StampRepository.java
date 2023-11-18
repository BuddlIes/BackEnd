package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.StampInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface StampRepository extends JpaRepository<StampInfo, Long> {

    Optional<StampInfo> findById(Long stamp_id);

    List<StampInfo> findAll();

    @Query("SELECT s, a FROM StampInfo s LEFT OUTER JOIN StampAcquireInfo a ON s.stamp_id = a.stampId WHERE a.userNumber =:user_number")
    List<Object[]> findStampInfoListWithAcquire(@Param("user_number") Long user_number);
}




