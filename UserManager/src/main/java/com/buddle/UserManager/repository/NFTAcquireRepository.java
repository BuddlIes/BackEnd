package com.buddle.UserManager.repository;

import com.buddle.UserManager.entity.NFTAcquireInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface NFTAcquireRepository extends JpaRepository<NFTAcquireInfo, Long> {

    //Optional<NFTAcquireInfo> findByAcquire_id(Long acquire_id);
    List<NFTAcquireInfo> findByUserNumber(Long user_number);

    Long countDistinctByUserNumberEquals(Long user_number);

    Optional<NFTAcquireInfo> findDistinctByUserNumberAndNftId(Long user_number, Long nft_id);
}
