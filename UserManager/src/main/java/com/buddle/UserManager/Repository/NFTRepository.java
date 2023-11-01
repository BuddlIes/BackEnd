package com.buddle.UserManager.Repository;

import com.buddle.UserManager.Entity.NFTInfo;
import com.buddle.UserManager.Entity.StampAcquireInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRepository extends JpaRepository<NFTInfo, Long> {


}
