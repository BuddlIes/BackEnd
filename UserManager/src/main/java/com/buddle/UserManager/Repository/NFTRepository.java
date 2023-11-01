package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.NFTInfo;
import com.buddle.UserManager.Entity.StampAcquireInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NFTRepository extends JpaRepository<NFTInfo, Long> {

    Optional<NFTInfo> findById(Long nft_id);

    @Query("SELECT n, a FROM NFTInfo n LEFT OUTER JOIN NFTAcquireInfo a ON n.nft_id = a.nftId WHERE a.userNumber =:user_number")
    List<Object[]> findNFTInfoListWithAcquire(@Param("user_number") Long user_number);

    @Query("SELECT n, a FROM NFTInfo n LEFT OUTER JOIN NFTAcquireInfo a ON n.nft_id = a.nftId WHERE a.userNumber =:user_number AND n.nft_id =:nft_id")
    Object[] findNFTInfoWithAcquire(@Param("user_number") Long user_number, @Param("nft_id") Long nft_id);

}
