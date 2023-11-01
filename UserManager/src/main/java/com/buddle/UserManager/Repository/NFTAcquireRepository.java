package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.NFTAcquireInfo;
import com.buddle.UserManager.Entity.StampAcquireInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NFTAcquireRepository extends JpaRepository<NFTAcquireInfo, Long> {

    //Optional<NFTAcquireInfo> findByAcquire_id(Long acquire_id);
    List<NFTAcquireInfo> findByUserNumber(Long user_number);

    Long countDistinctByUserNumberEquals(Long user_number);
}
