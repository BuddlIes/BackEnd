package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.StampInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface StampRepository extends JpaRepository<StampInfo, Long> {

    Optional<StampInfo> findById(Long stamp_id);
}
